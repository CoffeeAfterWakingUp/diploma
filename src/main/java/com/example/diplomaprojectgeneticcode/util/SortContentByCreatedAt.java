package com.example.diplomaprojectgeneticcode.util;

import com.example.diplomaprojectgeneticcode.dto.ContentDTO;

import java.util.Comparator;

public class SortContentByCreatedAt implements Comparator<ContentDTO> {

    @Override
    public int compare(ContentDTO o1, ContentDTO o2) {
        return o1.getCreatedAt().compareTo(o2.getCreatedAt());
    }
}
