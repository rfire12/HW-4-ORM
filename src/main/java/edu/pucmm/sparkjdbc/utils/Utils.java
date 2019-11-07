package edu.pucmm.sparkjdbc.utils;

import edu.pucmm.sparkjdbc.encapsulation.Tag;
import edu.pucmm.sparkjdbc.encapsulation.User;
import edu.pucmm.sparkjdbc.services.TagsServices;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public final class Utils {
    public static Set<Tag> arrayToTagsSet(String[] tags) {
        Set<Tag> tagList = new HashSet<Tag>();
        Tag newTag;
        String uniqueID;
        for (String tag : tags) {
            Tag myTag = TagsServices.getInstance().findByName(tag);
            if (myTag != null) {
                tagList.add(myTag);
            } else {
                uniqueID = UUID.randomUUID().toString();
                newTag = new Tag(uniqueID, tag);
                TagsServices.getInstance().create(newTag);
                tagList.add(newTag);
            }
        }
        return tagList;
    }

    public static String truncate(String value, int length) {
        // Ensure String length is longer than requested size.
        if (value.length() > length) {
            return value.substring(0, length);
        } else {
            return value;
        }
    }

}
