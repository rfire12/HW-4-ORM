package edu.pucmm.sparkjdbc.utils;

import edu.pucmm.sparkjdbc.encapsulation.Tag;

import java.util.ArrayList;

public final class Utils {
    public static ArrayList<Tag> arrayToTagList(String[] tags){
        ArrayList<Tag> tagList = new ArrayList<>();
        Tag newTag;
        for(String tag : tags){
            newTag = new Tag(tag);
            tagList.add(newTag);
        }
        return tagList;
    }

    public static Boolean isTagInArray(Tag tag, ArrayList<Tag> tags,){
        Boolean exists = false;
        for(Tag myTag : tags){
            if(myTag.getTag().equalsIgnoreCase(tag.getTag())){
                exists = true;
            }
        }
        return exists;
    }
}
