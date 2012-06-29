package utils

import models.User

object DemoData {
  
  def loadDemoData() {
    
    val jamesUser = User("James Ward", "james@demo.com", "password")
    
    User.create(jamesUser)
    
  }

}

/*
public class DemoData {

    public static final String CRESTED_BUTTE_COLORADO_REGION = "Crested Butte, Colorado";
    public static final String ZERMATT_SWITZERLAND_REGION = "Zermatt, Switzerland";
    public static final String WEST_MAROON_PASS_ROUTE = "West Maroon Pass";

    public static void loadDemoData() {
        Logger.info("Loading Demo Data");

        Region crestedButteRegion = new Region(CRESTED_BUTTE_COLORADO_REGION);
        S3Photo crestedButtePhoto = new S3Photo();
        crestedButtePhoto.bucket = "com.ubertracks.hike.dev";
        crestedButtePhoto.key = "32228dba-23ab-4449-bd05-90290d287df4.jpg";
        crestedButteRegion.photo = crestedButtePhoto;
        crestedButteRegion.save();

        Region zermattRegion = new Region(ZERMATT_SWITZERLAND_REGION);
        S3Photo zermattPhoto = new S3Photo();
        zermattPhoto.bucket = "com.ubertracks.hike.dev";
        zermattPhoto.key = "5e3ee5c3-3db4-42a4-920e-a4343d8e2e41.jpg";
        zermattRegion.photo = zermattPhoto;
        zermattRegion.save();

        User jamesUser = new User("james@demo.com", "password", "James Ward");
        jamesUser.isAdmin = true;
        jamesUser.save();

        User mattUser = new User("matt@demo.com", "password", "Matt Raible");
        mattUser.save();

        User randomUser = new User("random@demo.com", "password", "Some Dude");
        randomUser.save();

        Route westMaroonPassRoute = new Route(WEST_MAROON_PASS_ROUTE, "A very technical and very strenuous loop with awesome Denver and Red Rocks views.", 9.1, crestedButteRegion, "North of Gothic");
        westMaroonPassRoute.save();
        
        new Direction(westMaroonPassRoute, 1, "Start at the Matthews Winters parking lot.").save();
        new Direction(westMaroonPassRoute, 2, "Cross County Road 93 and ride up a consistently tough trail to almost the top of the hog back.").save();
        new Direction(westMaroonPassRoute, 3, "Turn right on the Dakota Ridge trail and ride through rocky terrain for about 1 mile.").save();
        new Direction(westMaroonPassRoute, 4, "After a short descent there will be an intersection.  Continue straight to some very difficult wooden ramps.").save();
        new Direction(westMaroonPassRoute, 5, "Continue on the ridge until a few switchbacks drop you down the east side of the ridge to Alameda Pkwy.").save();
        new Direction(westMaroonPassRoute, 6, "Take a right on the road and head to the saddle.").save();
        new Direction(westMaroonPassRoute, 7, "Before crossing the saddle, take a left onto the trail.").save();
        new Direction(westMaroonPassRoute, 8, "Cross over the ridge and drop down some switchbacks to Hogback Rd.").save();
        new Direction(westMaroonPassRoute, 9, "Cross the road and ride a short distance on Red Rocks Park Rd.").save();
        new Direction(westMaroonPassRoute, 10, "Shortly after Jurassic Rd turn right onto the Red Rocks trail.").save();
        new Direction(westMaroonPassRoute, 11, "Ride up Red Rocks trail and cross Red Rocks Trail Rd.").save();
        new Direction(westMaroonPassRoute, 12, "Turn right to stay on Red Rocks trail heading North.").save();
        new Direction(westMaroonPassRoute, 13, "Cross Red Rocks Loop Rd to stay on Red Rocks trail.").save();
        new Direction(westMaroonPassRoute, 14, "Climb through some steep switchbacks until you reach an intersection.").save();
        new Direction(westMaroonPassRoute, 15, "Turn right a climb up a very steep but short section to a nice plateau.").save();
        new Direction(westMaroonPassRoute, 16, "Traverse north towards Matthews Winters across rocky terrain.").save();
        new Direction(westMaroonPassRoute, 17, "When you reach an intersection turn left for a nice loop.").save();
        new Direction(westMaroonPassRoute, 18, "Climb up numerous rocky switchbacks.").save();
        new Direction(westMaroonPassRoute, 19, "At the top continue south along the upper plateau.").save();
        new Direction(westMaroonPassRoute, 20, "Descent some through switchbacks with massive waterbars to the intersection you were at a while ago.").save();
        new Direction(westMaroonPassRoute, 21, "Turn left to repeat the very steep climb up to the lower plateau.").save();
        new Direction(westMaroonPassRoute, 22, "Continue north back to Matthews Winters this time turning right at the base of the big climb.").save();
        new Direction(westMaroonPassRoute, 23, "Continue heading towards Matthews Winters until you reach the bike-only trail to the right.").save();
        new Direction(westMaroonPassRoute, 24, "Enjoy the flowy and fast ride back to your car.").save();

        new Rating(jamesUser, westMaroonPassRoute, 3).save();
        new Rating(mattUser, westMaroonPassRoute, 5).save();
        
        new Comment(jamesUser, westMaroonPassRoute, "This route is one of the best in the Denver area!").save();


        Route mtnFalcon = new Route("Mount Falcon", "Big climbs.  Fast descents.", 6.1, crestedButteRegion, "Golden / Morrison");
        mtnFalcon.save();

        new Direction(mtnFalcon, 1, "Start at the Mount Falcon East Parking Lot").save();
        new Direction(mtnFalcon, 2, "Ride west from the parking lot on the Castle Trail").save();
        new Direction(mtnFalcon, 3, "Stay on the Castle Trail to the picnic benches at the top").save();
        new Direction(mtnFalcon, 4, "Return the way you came").save();
    }

}
*/