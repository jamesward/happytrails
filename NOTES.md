Notes for Matt & James
======================

Technical Goals
---------------

* RESTful Service
* Server-side Templates
* Play2 w/ Java
* Grails
* Form Validation
* Data Pagination
* Auth
* Scheduled Jobs
* Atom / RSS
* Email Notifications
* Indexing
* Load Testing (Blitz)
* Performance Testing (YSlow, etc)
* S3 Integration
* Unit / Integration Testing


Presentation Agenda
-------------------

1) Framework Overviews

2) Emperical Data (Load Test, LOC, etc)

3) Developer Experience


Schedule
--------

* Week 1 - Data Model Definition
* Week 2 - Data Layer & URL Design
* Week 3 - Controllers & Auth
* Week 4 - Views
* Week 5 - Misc Polish


Data Model
----------

* Route

        Long id
        String name
        String distance
        Region region
        String Location
        List<Direction> directions
        URL mapUrl
        List<Rating> ratings
        List<Comment> comments
        Photo photo
        Date creationDate

* Direction

        Long id
        Integer stepNumber
        String instruction

* Region

        Long id
        String name

* Rating

        Long id
        User user
        Integer value
        Date creationDate

* Comment

        Long id
        User user
        String value
        Date creationDate

* User

        Long id
        String emailAddress
        String password
        String name
        Date creationDate

