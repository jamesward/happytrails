import models.*;

public class DemoData {

  public static void loadDemoData() {

    Region denverFrontRangeRegion = new Region("Denver Front Range");
    denverFrontRangeRegion.save();

    User jamesUser = new User("james@demo.com", "password", "James Ward");
    jamesUser.save();

    User mattUser = new User("matt@demo.com", "password", "Matt Raible");
    mattUser.save();

    User randomUser = new User("random@demo.com", "password", "Some Dude");
    randomUser.save();

    Route dakotaRedWintersRoute = new Route("Dakota Ridge, Red Rocks and Mathews Winters", "A very technical and very strenuous loop with awesome Denver and Red Rocks views.", 9.1, denverFrontRangeRegion, "Golden / Morrison");
    dakotaRedWintersRoute.save();

    Direction drwDirection1 = new Direction(1, "Start at the Matthews Winters parking lot.");
    drwDirection1.save();
    dakotaRedWintersRoute.directions.add(drwDirection1);

    Direction drwDirection2 = new Direction(2, "Cross County Road 93 and ride up a consistently tough trail to almost the top of the hog back.");
    drwDirection2.save();
    dakotaRedWintersRoute.directions.add(drwDirection2);

    Direction drwDirection3 = new Direction(3, "Turn right on the Dakota Ridge trail and ride through rocky terrain for about 1 mile.");
    drwDirection3.save();
    dakotaRedWintersRoute.directions.add(drwDirection3);

    Direction drwDirection4 = new Direction(4, "After a short descent there will be an intersection.  Continue straight to some very difficult wooden ramps.");
    drwDirection4.save();
    dakotaRedWintersRoute.directions.add(drwDirection4);

    Direction drwDirection5 = new Direction(5, "Continue on the ridge until a few switchbacks drop you down the east side of the ridge to Alameda Pkwy.");
    drwDirection5.save();
    dakotaRedWintersRoute.directions.add(drwDirection5);

    Direction drwDirection6 = new Direction(6, "Take a right on the road and head to the saddle.");
    drwDirection6.save();
    dakotaRedWintersRoute.directions.add(drwDirection6);

    Direction drwDirection7 = new Direction(7, "Before crossing the saddle, take a left onto the trail.");
    drwDirection7.save();
    dakotaRedWintersRoute.directions.add(drwDirection7);

    Direction drwDirection8 = new Direction(8, "Cross over the ridge and drop down some switchbacks to Hogback Rd.");
    drwDirection8.save();
    dakotaRedWintersRoute.directions.add(drwDirection8);

    Direction drwDirection9 = new Direction(9, "Cross the road and ride a short distance on Red Rocks Park Rd.");
    drwDirection9.save();
    dakotaRedWintersRoute.directions.add(drwDirection9);

    Direction drwDirection10 = new Direction(10, "Shortly after Jurassic Rd turn right onto the Red Rocks trail.");
    drwDirection10.save();
    dakotaRedWintersRoute.directions.add(drwDirection10);

    Direction drwDirection11 = new Direction(11, "Ride up Red Rocks trail and cross Red Rocks Trail Rd.");
    drwDirection11.save();
    dakotaRedWintersRoute.directions.add(drwDirection11);

    Direction drwDirection12 = new Direction(12, "Turn right to stay on Red Rocks trail heading North.");
    drwDirection12.save();
    dakotaRedWintersRoute.directions.add(drwDirection12);

    Direction drwDirection13 = new Direction(13, "Cross Red Rocks Loop Rd to stay on Red Rocks trail.");
    drwDirection13.save();
    dakotaRedWintersRoute.directions.add(drwDirection13);

    Direction drwDirection14 = new Direction(14, "Climb through some steep switchbacks until you reach an intersection.");
    drwDirection14.save();
    dakotaRedWintersRoute.directions.add(drwDirection14);

    Direction drwDirection15 = new Direction(15, "Turn right a climb up a very steep but short section to a nice plateau.");
    drwDirection15.save();
    dakotaRedWintersRoute.directions.add(drwDirection15);

    Direction drwDirection16 = new Direction(16, "Traverse north towards Matthews Winters across rocky terrain.");
    drwDirection16.save();
    dakotaRedWintersRoute.directions.add(drwDirection16);

    Direction drwDirection17 = new Direction(17, "When you reach an intersection turn left for a nice loop.");
    drwDirection17.save();
    dakotaRedWintersRoute.directions.add(drwDirection17);

    Direction drwDirection18 = new Direction(18, "Climb up numerous rocky switchbacks.");
    drwDirection18.save();
    dakotaRedWintersRoute.directions.add(drwDirection18);

    Direction drwDirection19 = new Direction(19, "At the top continue south along the upper plateau.");
    drwDirection19.save();
    dakotaRedWintersRoute.directions.add(drwDirection19);

    Direction drwDirection20 = new Direction(20, "Descent some through switchbacks with massive waterbars to the intersection you were at a while ago.");
    drwDirection20.save();
    dakotaRedWintersRoute.directions.add(drwDirection20);

    Direction drwDirection21 = new Direction(21, "Turn left to repeat the very steep climb up to the lower plateau.");
    drwDirection21.save();
    dakotaRedWintersRoute.directions.add(drwDirection21);

    Direction drwDirection22 = new Direction(22, "Continue north back to Matthews Winters this time turning right at the base of the big climb.");
    drwDirection22.save();
    dakotaRedWintersRoute.directions.add(drwDirection22);

    Direction drwDirection23 = new Direction(23, "Continue heading towards Matthews Winters until you reach the bike-only trail to the right.");
    drwDirection23.save();
    dakotaRedWintersRoute.directions.add(drwDirection23);

    Direction drwDirection24 = new Direction(24, "Enjoy the flowy and fast ride back to your car.");
    drwDirection24.save();
    dakotaRedWintersRoute.directions.add(drwDirection24);

    Rating drwRating1 = new Rating(jamesUser, 4);
    drwRating1.save();
    dakotaRedWintersRoute.ratings.add(drwRating1);

    Rating drwRating2 = new Rating(mattUser, 5);
    drwRating2.save();
    dakotaRedWintersRoute.ratings.add(drwRating2);

    Comment drwComment1 = new Comment(jamesUser, "This route is one of the best in the Denver area!");
    drwComment1.save();
    dakotaRedWintersRoute.comments.add(drwComment1);

  }

}
