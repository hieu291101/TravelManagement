package com.lth.service;

import com.lth.pojos.Feedback;

import java.util.List;

public interface FeedbackService {
    Object[] getRatingByTourId(int tourId);

    List<Feedback> getFeedbacks(int tourId);

    Feedback addFeedback(int rating, String comment, int tourId, int userId);

}
