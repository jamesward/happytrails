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
* Username only available on secured resources - workaround: get it directly from the session

* Typesafe reverse routeing is nice - makes refactoring URLs very easy

* Dualing compilers (~run, ~test, IntelliJ)
* No auto CRUD UI

* Old Postgres version possibly incompatible with EBean

* HTTP Port config different than the other config

* EBean Pain: Lazy Loading doesn't work from Scala Templates

* Scala Console on Heroku

        heroku run bash
        java -Dconfig.file=conf/prod.conf -jar ~/.sbt_home/bin/sbt-launch-0.11.3-2.jar
        set fullClasspath in Compile += Attributed.blank(file("target/staged/*"))
        console
        import play.core.StaticApplication
        new StaticApplication(new java.io.File("."))
