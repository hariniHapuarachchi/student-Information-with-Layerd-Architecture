package lk.ijse.dep.business.custom;

import lk.ijse.dep.business.SuperBO;
import lk.ijse.dep.dto.BatchDetDTO;
import lk.ijse.dep.dto.CourseDetDTO;

import java.util.List;

public interface ManageCoursesBO extends SuperBO {

    List<CourseDetDTO> getCourses() throws Exception;

    boolean CreateCourse(CourseDetDTO dto) throws Exception;

    boolean updateCourse(CourseDetDTO dto) throws Exception;

    boolean deleteCourse(String batchID) throws Exception;

    CourseDetDTO findCourse(String id) throws Exception;
}
