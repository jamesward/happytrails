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

1. Framework Overviews
2. Emperical Data (Load Test, LOC, etc)
3. Developer Experience


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
        String location
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
        Photo photo
        Date creationDate

* User

        Long id
        String emailAddress
        String shaPassword
        String name
        Date creationDate

* Photo

        Long id
        URL thumbnailUrl
        URL fullUrl


URL Design
----------

    GET     /                           # Display list of Regions

    GET     /signup                     # Signup Form
    POST    /signup                     # Save Signup Data

    POST    /login                      # Login

    GET     /feed/:region               # RSS Feed for the specified Region
    GET     /feed/:region/:route        # RSS Feed for the specified Route

    POST    /subscribe/:region          # Subscribe to Region Updates via Email

    GET     /:region/addroute           # Add Route to a Region Form
    POST    /:region/addroute           # Save Route to a Region

    POST    /:region/:route/addrating   # Add Rating to a Route

    GET     /:region/:route/addcomment  # Add Comment to a Route Form
    POST    /:region/:route/addcomment  # Save Comment to a Route

    GET     /:region                    # Display list of Routes in a Region


Open Questions
--------------

* Should we add a property to Region and Route for using their names in a URL?  Or just have a set algorithm for doing it?  (e.g. turn "Dakota Ridge, Red Rocks, Matthews Winters Loop" into "dakota_ridge_red_rocks_matthews_winters_loop")
* Should we define data constraints here?