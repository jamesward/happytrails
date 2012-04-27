import models.Region;
import models.User;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

public class DemoData {

  public static void loadDemoData() {

    Region denverFrontRangeRegion = new Region("Denver Front Range");
    denverFrontRangeRegion.save();

    User jamesUser = new User("james@demo.com", "password", "James Ward");
    jamesUser.save();

    /*
    INSERT INTO account (id, email_address, sha_password, full_name, creation_date) VALUES
            (1, 'admin@demo.com', {-79, 9, -13}, 'Admin John', now());
    INSERT INTO account (id, email_address, sha_password, full_name, creation_date) VALUES
            (2, 'user@demo.com', x'9151440965cf9c5e07f81eee6241c042a7b78e9bb2dd4f928a8f6da5e369cdffdd2b70c70663ee30d02115731d35f1ece5aad9b362aaa9850efa99e3d197212a', 'User Betty', now());

    INSERT INTO route (id, name, description, distance, region_id, location, creation_date) VALUES
            (1, 'Dakota Ridge, Red Rocks and Mathews Winters', 'A very technical and very strenuous loop with awesome Denver and Red Rocks views.', '9.1 miles', 1, 'Golden / Morrison', now());

    INSERT INTO direction (id, route_id, step_number, instruction) VALUES (1, 1, 1, 'Start at the Matthews Winters parking lot.');
    INSERT INTO direction (id, route_id, step_number, instruction) VALUES (2, 1, 2, 'Cross County Road 93 and ride up a consistently tough trail to almost the top of the hog back.');
    INSERT INTO direction (id, route_id, step_number, instruction) VALUES (3, 1, 3, 'Turn right on the Dakota Ridge trail and ride through rocky terrain for about 1 mile.');
    INSERT INTO direction (id, route_id, step_number, instruction) VALUES (4, 1, 4, 'After a short descent there will be an intersection.  Continue straight to some very difficult wooden ramps.');
    INSERT INTO direction (id, route_id, step_number, instruction) VALUES (5, 1, 5, 'Continue on the ridge until a few switchbacks drop you down the east side of the ridge to Alameda Pkwy.');
    INSERT INTO direction (id, route_id, step_number, instruction) VALUES (6, 1, 6, 'Take a right on the road and head to the saddle.');
    INSERT INTO direction (id, route_id, step_number, instruction) VALUES (7, 1, 7, 'Before crossing the saddle, take a left onto the trail.');
    INSERT INTO direction (id, route_id, step_number, instruction) VALUES (8, 1, 8, 'Cross over the ridge and drop down some switchbacks to Hogback Rd.');
    INSERT INTO direction (id, route_id, step_number, instruction) VALUES (9, 1, 9, 'Cross the road and ride a short distance on Red Rocks Park Rd.');
    INSERT INTO direction (id, route_id, step_number, instruction) VALUES (10, 1, 10, 'Shortly after Jurassic Rd turn right onto the Red Rocks trail.');
    INSERT INTO direction (id, route_id, step_number, instruction) VALUES (11, 1, 11, 'Ride up Red Rocks trail and cross Red Rocks Trail Rd.');
    INSERT INTO direction (id, route_id, step_number, instruction) VALUES (12, 1, 12, 'Turn right to stay on Red Rocks trail heading North.');
    INSERT INTO direction (id, route_id, step_number, instruction) VALUES (13, 1, 13, 'Cross Red Rocks Loop Rd to stay on Red Rocks trail.');
    INSERT INTO direction (id, route_id, step_number, instruction) VALUES (14, 1, 14, 'Climb through some steep switchbacks until you reach an intersection.');
    INSERT INTO direction (id, route_id, step_number, instruction) VALUES (15, 1, 15, 'Turn right a climb up a very steep but short section to a nice plateau.');
    INSERT INTO direction (id, route_id, step_number, instruction) VALUES (16, 1, 16, 'Traverse north towards Matthews Winters across rocky terrain.');
    INSERT INTO direction (id, route_id, step_number, instruction) VALUES (17, 1, 17, 'When you reach an intersection turn left for a nice loop.');
    INSERT INTO direction (id, route_id, step_number, instruction) VALUES (18, 1, 18, 'Climb up numerous rocky switchbacks.');
    INSERT INTO direction (id, route_id, step_number, instruction) VALUES (19, 1, 19, 'At the top continue south along the upper plateau.');
    INSERT INTO direction (id, route_id, step_number, instruction) VALUES (20, 1, 20, 'Descent some through switchbacks with massive waterbars to the intersection you were at a while ago.');
    INSERT INTO direction (id, route_id, step_number, instruction) VALUES (21, 1, 21, 'Turn left to repeat the very steep climb up to the lower plateau.');
    INSERT INTO direction (id, route_id, step_number, instruction) VALUES (22, 1, 22, 'Continue north back to Matthews Winters this time turning right at the base of the big climb.');
    INSERT INTO direction (id, route_id, step_number, instruction) VALUES (23, 1, 23, 'Continue heading towards Matthews Winters until you reach the bike-only trail to the right.');
    INSERT INTO direction (id, route_id, step_number, instruction) VALUES (24, 1, 24, 'Enjoy the flowy and fast ride back to your car.');

    INSERT INTO rating (id, route_id, user_id, value, creation_date) VALUES (1, 1, 1, 4, now());
    INSERT INTO rating (id, route_id, user_id, value, creation_date) VALUES (2, 1, 2, 5, now());

    INSERT INTO comment (id, route_id, user_id, value, creation_date) VALUES (1, 1, 1, 'This route is one of the best in the Denver area!', now());
    */

  }

}
