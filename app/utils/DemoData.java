package utils;

import models.*;
import play.Logger;

public class DemoData {

    public static void loadDemoData() {
        Logger.info("Loading Demo Data");

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
        
        new Direction(dakotaRedWintersRoute, 1, "Start at the Matthews Winters parking lot.").save();
        new Direction(dakotaRedWintersRoute, 2, "Cross County Road 93 and ride up a consistently tough trail to almost the top of the hog back.").save();
        new Direction(dakotaRedWintersRoute, 3, "Turn right on the Dakota Ridge trail and ride through rocky terrain for about 1 mile.").save();
        new Direction(dakotaRedWintersRoute, 4, "After a short descent there will be an intersection.  Continue straight to some very difficult wooden ramps.").save();
        new Direction(dakotaRedWintersRoute, 5, "Continue on the ridge until a few switchbacks drop you down the east side of the ridge to Alameda Pkwy.").save();
        new Direction(dakotaRedWintersRoute, 6, "Take a right on the road and head to the saddle.").save();
        new Direction(dakotaRedWintersRoute, 7, "Before crossing the saddle, take a left onto the trail.").save();
        new Direction(dakotaRedWintersRoute, 8, "Cross over the ridge and drop down some switchbacks to Hogback Rd.").save();
        new Direction(dakotaRedWintersRoute, 9, "Cross the road and ride a short distance on Red Rocks Park Rd.").save();
        new Direction(dakotaRedWintersRoute, 10, "Shortly after Jurassic Rd turn right onto the Red Rocks trail.").save();
        new Direction(dakotaRedWintersRoute, 11, "Ride up Red Rocks trail and cross Red Rocks Trail Rd.").save();
        new Direction(dakotaRedWintersRoute, 12, "Turn right to stay on Red Rocks trail heading North.").save();
        new Direction(dakotaRedWintersRoute, 13, "Cross Red Rocks Loop Rd to stay on Red Rocks trail.").save();
        new Direction(dakotaRedWintersRoute, 14, "Climb through some steep switchbacks until you reach an intersection.").save();
        new Direction(dakotaRedWintersRoute, 15, "Turn right a climb up a very steep but short section to a nice plateau.").save();
        new Direction(dakotaRedWintersRoute, 16, "Traverse north towards Matthews Winters across rocky terrain.").save();
        new Direction(dakotaRedWintersRoute, 17, "When you reach an intersection turn left for a nice loop.").save();
        new Direction(dakotaRedWintersRoute, 18, "Climb up numerous rocky switchbacks.").save();
        new Direction(dakotaRedWintersRoute, 19, "At the top continue south along the upper plateau.").save();
        new Direction(dakotaRedWintersRoute, 20, "Descent some through switchbacks with massive waterbars to the intersection you were at a while ago.").save();
        new Direction(dakotaRedWintersRoute, 21, "Turn left to repeat the very steep climb up to the lower plateau.").save();
        new Direction(dakotaRedWintersRoute, 22, "Continue north back to Matthews Winters this time turning right at the base of the big climb.").save();
        new Direction(dakotaRedWintersRoute, 23, "Continue heading towards Matthews Winters until you reach the bike-only trail to the right.").save();
        new Direction(dakotaRedWintersRoute, 24, "Enjoy the flowy and fast ride back to your car.").save();

        new Rating(jamesUser, dakotaRedWintersRoute, 3).save();
        new Rating(mattUser, dakotaRedWintersRoute, 5).save();
        
        new Comment(jamesUser, dakotaRedWintersRoute, "This route is one of the best in the Denver area!").save();


        Route mtnFalcon = new Route("Mount Falcon", "Big climbs.  Fast descents.", 6.1, denverFrontRangeRegion, "Golden / Morrison");
        mtnFalcon.save();

        new Direction(mtnFalcon, 1, "Start at the Mount Falcon East Parking Lot").save();
        new Direction(mtnFalcon, 2, "Ride west from the parking lot on the Castle Trail").save();
        new Direction(mtnFalcon, 3, "Stay on the Castle Trail to the picnic benches at the top").save();
        new Direction(mtnFalcon, 4, "Return the way you came").save();
    }

}
