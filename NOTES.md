Notes on Play 2
===============

Experience Notes
----------------

* `play idea` doesn't generate project files
* Evolutions not seeing sql changes
* `play ~test` is very nice
* Unhelpful compile error:

        [error] {file:/home/jamesw/projects/happytrails/}happytrails/compile:sources: PlayException: Compilation error [Missing parameter in call definition: region]

* Can't run `play idea` with compile errors
* `@Constraints.Required` does not do `@Column(nullable = false)`
* `play ~test` after some time produces `Error during sbt execution: java.lang.OutOfMemoryError: PermGen space`
* Strange error:

    [error] Test CommentTest.testCreate failed: Error inserting bean [class models.Comment] with unidirectional relationship. For inserts you must use cascade save on the master bean [class models.Route].

* Testing constraints???
* Logger in test not working
* Unit testing Form not working
* I wish `play ~test` only ran the tests that changed
* Getting request params directly is difficult