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

    dakotaRedWintersRoute.directions.add(new Direction(1, "Start at the Matthews Winters parking lot."));
    dakotaRedWintersRoute.directions.add(new Direction(2, "Cross County Road 93 and ride up a consistently tough trail to almost the top of the hog back."));
    dakotaRedWintersRoute.directions.add(new Direction(3, "Turn right on the Dakota Ridge trail and ride through rocky terrain for about 1 mile."));
    dakotaRedWintersRoute.directions.add(new Direction(4, "After a short descent there will be an intersection.  Continue straight to some very difficult wooden ramps."));
    dakotaRedWintersRoute.directions.add(new Direction(5, "Continue on the ridge until a few switchbacks drop you down the east side of the ridge to Alameda Pkwy."));
    dakotaRedWintersRoute.directions.add(new Direction(6, "Take a right on the road and head to the saddle."));
    dakotaRedWintersRoute.directions.add(new Direction(7, "Before crossing the saddle, take a left onto the trail."));
    dakotaRedWintersRoute.directions.add(new Direction(8, "Cross over the ridge and drop down some switchbacks to Hogback Rd."));
    dakotaRedWintersRoute.directions.add(new Direction(9, "Cross the road and ride a short distance on Red Rocks Park Rd."));
    dakotaRedWintersRoute.directions.add(new Direction(10, "Shortly after Jurassic Rd turn right onto the Red Rocks trail."));
    dakotaRedWintersRoute.directions.add(new Direction(11, "Ride up Red Rocks trail and cross Red Rocks Trail Rd."));
    dakotaRedWintersRoute.directions.add(new Direction(12, "Turn right to stay on Red Rocks trail heading North."));
    dakotaRedWintersRoute.directions.add(new Direction(13, "Cross Red Rocks Loop Rd to stay on Red Rocks trail."));
    dakotaRedWintersRoute.directions.add(new Direction(14, "Climb through some steep switchbacks until you reach an intersection."));
    dakotaRedWintersRoute.directions.add(new Direction(15, "Turn right a climb up a very steep but short section to a nice plateau."));
    dakotaRedWintersRoute.directions.add(new Direction(16, "Traverse north towards Matthews Winters across rocky terrain."));
    dakotaRedWintersRoute.directions.add(new Direction(17, "When you reach an intersection turn left for a nice loop."));
    dakotaRedWintersRoute.directions.add(new Direction(18, "Climb up numerous rocky switchbacks."));
    dakotaRedWintersRoute.directions.add(new Direction(19, "At the top continue south along the upper plateau."));
    dakotaRedWintersRoute.directions.add(new Direction(20, "Descent some through switchbacks with massive waterbars to the intersection you were at a while ago."));
    dakotaRedWintersRoute.directions.add(new Direction(21, "Turn left to repeat the very steep climb up to the lower plateau."));
    dakotaRedWintersRoute.directions.add(new Direction(22, "Continue north back to Matthews Winters this time turning right at the base of the big climb."));
    dakotaRedWintersRoute.directions.add(new Direction(23, "Continue heading towards Matthews Winters until you reach the bike-only trail to the right."));
    dakotaRedWintersRoute.directions.add(new Direction(24, "Enjoy the flowy and fast ride back to your car."));

    dakotaRedWintersRoute.ratings.add(new Rating(jamesUser, 4));
    dakotaRedWintersRoute.ratings.add(new Rating(mattUser, 5));

    dakotaRedWintersRoute.comments.add(new Comment(jamesUser, "This route is one of the best in the Denver area!"));

    dakotaRedWintersRoute.save();

  }

}
