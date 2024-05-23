package uz.pdp.studycenter.model.resp;



public record StudentInfoResponseDto(Long id, String firstName, String lastName, String phoneNumber, Long balance, String timeTable, Integer currentLesson, String groupName, String courseName) {
}
