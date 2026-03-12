# Lab 3 Reflection — Dirty Checking

"Dirty checking" in JPA means that when an entity is loaded into the persistence
context via `em.find()`, JPA takes an internal snapshot of its state. If any field
on that managed entity is changed before the transaction commits, JPA automatically
detects the difference between the snapshot and the current state, then issues an
`UPDATE` SQL statement — without the developer ever calling `em.merge()`,
`em.update()`, or any explicit persistence method.

In this lab, dirty checking was demonstrated in both `updateTitle()` and
`updateSalary()` in `EmployeeService`. In each method, `em.find()` loaded the
`Employee` into the persistence context as a managed entity, then a setter was
called to change a field. Because both methods are annotated with `@Transactional`,
when the method returned and the transaction committed, EclipseLink compared the
entity's current state to the original snapshot, detected the change, and
automatically flushed an `UPDATE` to the database. The subsequent `find` action
in the servlet confirmed the updated value was persisted, proving that no explicit
update call was necessary.