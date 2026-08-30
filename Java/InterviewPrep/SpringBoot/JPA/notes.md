## Spring Data JPA
Spring Data JPA is a framework that eliminates data-access boilerplate code by allowing you to manage database interactions through plain Java interfaces.

#### 1. Explain the Persistence Context.
The Persistence Context is an in-memory staging area and First-Level Cache managed by JPA's EntityManager. It acts as a buffer between your application's Java objects and the database, tracking all entity instances and managing their lifecycles during a transaction.
```
+-------------------------------------------------------------+
|                     SPRING / APPLICATION                    |
|  User user = entityManager.find(User.class, 1L);            |
+-------------------------------------------------------------+
                              |
                              v
+-------------------------------------------------------------+
|                     PERSISTENCE CONTEXT                     |
|  - First-Level Cache (Map of ID -> Entity)                  |
|  - Tracks entity modifications (Dirty Checking)             |
|  - Maintains object identity (userA == userB)              |
+-------------------------------------------------------------+
                              | (Flush on Commit)
                              v
+-------------------------------------------------------------+
|                      DATABASE (SQL)                         |
+-------------------------------------------------------------+
```

#### Explain the states in the Persistent Context
In JPA and Hibernate, an entity's state defines its relationship with the Persistence Context and determines whether changes made to the object will automatically synchronize with the database.

- Transient : A newly instantiated Java object (new User()) that has never been associated with a Persistence Context.
- Managed : An entity that is currently attached to an active Persistence Context and has a database identity (primary key).
- Detached : An entity that has a database identity (ID), but its associated Persistence Context has closed or been evicted.
- Removed : An entity scheduled for deletion from the database.
