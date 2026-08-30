# 🗄️ Section 7 — Schema Management (`ddl-auto`) & JPA Buddy Plugin

Section 7 covers how to control Hibernate's database schema management using `spring.jpa.hibernate.ddl-auto`, and how to speed up JPA development in IntelliJ IDEA using the **JPA Buddy** plugin.

---

## 📚 Topics Covered

* 🧬 `spring.jpa.hibernate.ddl-auto` and its modes (`create`, `create-drop`, `create-only`, `drop`, `update`, `validate`, `truncate`, `none`)
* 🌍 Recommended `ddl-auto` value per environment (local dev / testing / production)
* 🐶 JPA Buddy plugin for IntelliJ IDEA

---

## 🧬 `spring.jpa.hibernate.ddl-auto`

Based on the value assigned to this property, Hibernate decides what to do with your database schema. Depending on the value, it can create, update, validate, or delete your schema.

| Value          | Meaning                                                              | Use Case                                  |
| -------------- | --------------------------------------------------------------------- | ------------------------------------------ |
| `create`       | Drops existing tables, then creates new ones from scratch each start | Early development, testing fresh schema. ⚠️ All existing data is lost |
| `create-drop`  | Creates tables on startup, drops them on shutdown                    | Unit tests, in-memory databases            |
| `create-only`  | Only creates schema, never drops                                     | Initial migrations / one-off setup         |
| `drop`         | Drops all tables, creates nothing                                    | Rare — mostly for tools or scripted cleanup |
| `update`       | Updates schema if necessary (adds columns, tables)                   | Development only. ⚠️ Not safe for production — can break schema or cause silent issues |
| `validate`     | Checks if the DB schema matches entities. No changes made            | Staging / Production                       |
| `truncate`     | Deletes all rows but keeps table structure                           | Resetting test data without dropping tables |
| `none`         | Hibernate will NOT manage the schema. The DB admin is the boss        | Production (recommended)                   |

### Recommended usage

| Environment | Recommended Value            |
| ----------- | ----------------------------- |
| Local Dev   | `update`, `create`, `create-drop` |
| Testing     | `create-drop`, `validate`     |
| Production  | `none` or `validate`          |

> Defaults to `create-drop` when using an embedded database and no schema manager was detected. Otherwise, defaults to `none`.

---

## 🐶 JPA Buddy Plugin (IntelliJ IDEA)

A powerful IntelliJ IDEA plugin for working with JPA, Hibernate, and Spring Data. Helps generate entities, repositories, DTOs, and database mappings. Provides easy tools to navigate, validate, and improve your JPA layer, and reduces boilerplate and eliminates common mistakes in JPA development.

**Top features:**
- Entity Generation Wizard
- Relationship Builder (`@OneToOne`, `@OneToMany`, etc.)
- Repository/DAO Generator
- JSON → Entity conversion
- Database Schema Synchronization
- Attribute/Column Type Suggestions
- JPA Error Inspection (real-time warnings)

**Installing JPA Buddy:**
1. Go to `File → Settings → Plugins`
2. Search for "JPA Buddy" and "Jakarta EE: JPA Model"
3. Install both of them & restart IntelliJ IDEA

> JetBrains premium version gives extra features compared to the free version.

---

### 🚀 Keep Learning. Keep Building. Keep Improving.