package courses.enrollments.application.inboundports;

public record AnnounceCommand(String code, String title, int limit) {
}
