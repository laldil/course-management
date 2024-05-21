package kz.edu.astanait.courseservice.model.enums;

import java.util.Date;

/**
 * @author aldi
 * @since 20.05.2024
 */
public enum SubmissionStatus {
    LATE_SUBMISSION,

    EARLY_SUBMISSION;

    public static SubmissionStatus getStatus(Date endDate, Date submissionDate) {
        return submissionDate.after(endDate) ? LATE_SUBMISSION : EARLY_SUBMISSION;
    }
}
