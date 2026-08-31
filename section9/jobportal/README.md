# 🗄️ Section 9 — JPA Entity Relationships, Cascade & Fetch Strategies

Section 9 dives into how **Spring Data JPA** models real-world relationships between entities, using the Job Portal's `Company` and `Job` entities as the running example.

---

## 📚 Topics Covered

* 🔗 Understanding relationships in JPA (`@OneToOne`, `@OneToMany`, `@ManyToOne`, `@ManyToMany`)
* 🏢 Modeling a real `Company` → `Job` relationship
* 🧭 Owning side vs. inverse side (`mappedBy`)
* ♻️ Cascade types (`PERSIST`, `MERGE`, `REMOVE`, `REFRESH`, `DETACH`, `ALL`)
* 🗑️ `orphanRemoval`
* ⚡ Fetch types (`EAGER` vs `LAZY`) and their defaults per relationship
* 🔑 `@JoinColumn` and foreign key mapping
* 💣 `@OnDelete(action = OnDeleteAction.CASCADE)` — database-level cascading deletes
* ⚖️ `@OnDelete` vs `CascadeType.REMOVE`
* 🔄 Bidirectional relationships

---

## 🧩 Why Relationships Matter

In real apps, data is connected — a customer has an address, a book has an author. JPA relationships model these connections between entities (tables) so you don't have to write manual join queries or manage foreign keys by hand.

Spring Data JPA supports four main relationship types:

| Type | Meaning |
|---|---|
| `@OneToOne` | One entity is linked to exactly one other entity |
| `@OneToMany` | One entity is linked to multiple entities |
| `@ManyToOne` | Multiple entities are linked to one entity |
| `@ManyToMany` | Multiple entities are linked to multiple entities |

---

## 🏢 Case Study: Company & Jobs

Imagine a company like Amazon posting multiple job openings — Java Developer, Tester, DevOps Engineer, Cloud Architect.

**One Company → Many Jobs.** Each Job belongs to only one Company. This is exactly what `@OneToMany` / `@ManyToOne` represents.

```
Company  ──One To Many──▶   [Job, Job, Job, Job]
Jobs     ──Many To One──▶   Company
```

### How the database looks

**Companies table**

| ID | Name |
|---|---|
| 1 | Amazon |

**Jobs table** — `company_id` is the foreign key that creates the relationship

| ID | Title | company_id |
|---|---|---|
| 101 | Java Dev | 1 |
| 102 | QA Automation | 1 |
| 103 | DevOps Eng | 1 |
| 104 | Cloud Architect | 1 |

### Why not `@OneToOne` between Company and Job?

It's tempting to think "every job belongs to one company, so it should be `@OneToOne`" — but that logic only looks at the Job side, not both sides.

In JPA (and in databases), `@OneToOne` means **both sides** have exactly one:
- One company → only one job, and one job → only one company.

That's not true here — one company can have many jobs.

**Key principle:** `@OneToOne` means both sides have exactly one. `@OneToMany` means one side has many, while the other side has only one.

---

## 🧱 Entity Mapping

### `Company.java`

```java
@Entity
@Table(name = "companies")
public class Company extends BaseEntity {

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Job> jobs = new ArrayList<>();
}
```

- **`mappedBy = "company"`** — the `Job` entity owns the foreign key, via a field named `company`. Company is just the *inverse* side telling JPA "look at that field on the other side."
- **`cascade = CascadeType.ALL`** — if something happens to the parent (`Company`), do the same to its children (`Jobs`): saving a company saves its jobs, updating a company updates its jobs, deleting a company deletes all its jobs.
- **`orphanRemoval = true`** — if a job is removed from the `jobs` list (`company.getJobs().remove(job)`), it becomes an "orphan" and Hibernate deletes it from the database too. Without it, a removed job just stays in the database, unlinked to any company. With it, the database stays clean automatically.
- **`private List<Job> jobs = new ArrayList<>();`** — initializes the list so it's never `null`. This means `company.getJobs()` always returns an empty list (not `null`), and you can safely call `company.getJobs().add(job)`.

### `Job.java`

```java
@Entity
@Table(name = "jobs")
public class Job extends BaseEntity {

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;
}
```

- **`@NotNull`** — every job must belong to a company; saving a job with `company = null` results in a validation error.
- **`@ManyToOne`** — many jobs belong to one company: a company can have many job posts, but each job is linked to exactly one company.
- **`optional = false`** — the company relationship is mandatory; every job must have a company. This reinforces `@NotNull`.
- **`fetch = FetchType.LAZY`** — improves performance: when you load a job, Hibernate does **not** load the related company immediately. It only fetches the company when you actually call `job.getCompany()`. You usually need job details first and don't always need the company details, so this saves memory and reduces queries.
- **`@JoinColumn(name = "company_id", nullable = false)`** — tells Hibernate there's a column named `company_id` in the `jobs` table that stores the company's ID. This is the foreign key.
- **`@OnDelete(action = OnDeleteAction.CASCADE)`** — tells the **database** (not just Hibernate): "if a company is deleted, automatically delete all its jobs." This ensures referential integrity at the database level.

**Example:** saving a company automatically saves both jobs because of cascade:

```java
Company company = new Company();
Job job1 = new Job();
Job job2 = new Job();
company.getJobs().add(job1);
company.getJobs().add(job2);

// This saves company AND both jobs automatically
companyRepository.save(company);
```

If Company 1 is deleted, all rows in `jobs` with `company_id = 1` are automatically deleted by the database — no orphan jobs, no mismatch errors.

---

## 🧭 Who Owns the Relationship?

From the JPA perspective:

- **Job is the owner** — because it has the foreign key column `company_id`.
- **Company is the "inverse" side** — `mappedBy = "company"` tells JPA: "use the foreign key in the `Job` table, I don't have one." This prevents duplicate join tables and keeps the database clean.

---

## ⚡ Understanding Fetch Types in JPA

Fetch types define **when** related entities are loaded from the database. When you define relationships like `@OneToOne`, `@OneToMany`, `@ManyToOne`, `@ManyToMany`, JPA needs to know: should the related data load immediately with the parent, or only when needed?

| Fetch Type | Meaning | Behavior |
|---|---|---|
| `EAGER` | Fetch immediately | Loads the related entity right away |
| `LAZY` | Fetch when accessed (on demand) | Loads the related entity only when used |

**Think of it like ordering food 🍔**
- `EAGER` = you order the main dish + side dish at the same time — even if you're not hungry for the side, it still comes.
- `LAZY` = you order only the main dish, and ask for the side dish only if/when you want it.

### Default fetch types per relationship

| Relationship Annotation | Default Fetch Type |
|---|---|
| `@OneToOne` | EAGER |
| `@ManyToOne` | EAGER |
| `@OneToMany` | LAZY |
| `@ManyToMany` | LAZY |

> Note: in the `Job` entity above, `fetch = FetchType.LAZY` is set explicitly on `@ManyToOne`, overriding its default of `EAGER`.

---

## ♻️ Understanding Cascade Types in JPA

Cascade in JPA means: "if I do something to the parent entity, automatically do it to the child entity too." Think of it like a chain reaction — changes made to one entity get automatically applied to related entities.

**Real-world analogy:** if a Company is saved, its Jobs should be saved too. If the Company is deleted, its Jobs should be deleted too. That's cascading — you don't need to save or delete related objects separately.

### Why use cascade?

- To reduce boilerplate code
- To avoid manually saving/deleting related entities
- To keep entities in sync automatically

### Available cascade types

| Cascade Type | What It Does |
|---|---|
| `PERSIST` | Saves the child entity when the parent is saved |
| `MERGE` | Updates the child entity when the parent is updated |
| `REMOVE` | Deletes the child entity when the parent is deleted |
| `REFRESH` | Refreshes the child when the parent is refreshed from the DB |
| `DETACH` | Detaches the child from the persistence context when the parent is detached |
| `ALL` | Applies all of the above actions |

---

## 💣 `@OnDelete(action = OnDeleteAction.CASCADE)`

`@OnDelete` is a Hibernate/JPA annotation that tells the **database** to automatically delete related records when a parent record is deleted — it uses `ON DELETE CASCADE` at the database level (i.e., in the generated SQL), rather than Hibernate deleting rows one by one in application code.

```java
@Entity
@Table(name = "jobs")
public class Job extends BaseEntity {

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;
}
```

If a Company is deleted, the associated job records are automatically deleted by the database.

### `OnDeleteAction` values

| Enum Value | Description |
|---|---|
| `NO_ACTION` | Default behavior. If a parent is deleted while children still exist, the database raises a foreign key constraint violation (possibly later, in transaction) |
| `CASCADE` | Automatically deletes the child rows when the parent row is deleted (via `ON DELETE CASCADE` at DB level) |
| `RESTRICT` | Immediately blocks the deletion of the parent if any child exists (stricter than `NO_ACTION`) |
| `SET_NULL` | Sets the child's foreign key to `NULL` when the parent is deleted (child remains, but unlinked) |
| `SET_DEFAULT` | Sets the child's foreign key to its default value (if any) when the parent is deleted |

### `@OnDelete` vs `CascadeType.REMOVE`

| Feature | `@OnDelete` | `CascadeType.REMOVE` |
|---|---|---|
| Where it runs | Database level (SQL foreign key) | Application level (Java/Hibernate) |
| How it works | Database deletes child records automatically | Hibernate deletes child records in code |
| Performance | Faster (database handles it) | Slower (Hibernate queries each record) |
| Code example | `@OnDelete(action = OnDeleteAction.CASCADE)` | `cascade = CascadeType.REMOVE` |
| When to use | When you want the database to manage deletions | When you need application-level control |

> **Key difference:** `@OnDelete` is a database rule; `CascadeType` is a Hibernate instruction.

---

## 🔄 What Is a Bidirectional Relationship?

In JPA, a bidirectional relationship means both entities (`Company` and `Job`) know about each other, and you can navigate the relationship in both directions:

- From `Company`, you can get all its Jobs: `company.getJobs()`
- From `Job`, you can get the Company it belongs to: `job.getCompany()`

It's like two friends who both have each other's phone numbers — they can call each other anytime.

**In our case:**
- `Company` → has many jobs (`@OneToMany` side)
- `Job` → belongs to one company (`@ManyToOne` side)

Because both sides reference each other, the relationship becomes bidirectional.

---

## 🛠️ Technologies Used in This Section

```text
🌱 Spring Data JPA
💾 Hibernate
🛢️ Relational database (MySQL / H2)
📦 Jakarta Validation (@NotNull)
```

---

### 🚀 Keep Learning. Keep Building. Keep Improving.