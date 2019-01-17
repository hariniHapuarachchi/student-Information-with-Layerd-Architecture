package lk.ijse.dep.business.custom.impl;

import lk.ijse.dep.business.Converter;
import lk.ijse.dep.business.custom.ManageCoursesBO;
import lk.ijse.dep.dao.DAOFactory;
import lk.ijse.dep.dao.custom.CourseDetDAO;
import lk.ijse.dep.dto.CourseDetDTO;

import java.util.List;

public class ManageCoursesBOImpl implements ManageCoursesBO {

    private CourseDetDAO courseDetDAO;

    public ManageCoursesBOImpl() {
        courseDetDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.COURSE);
    }

    @Override
    public List<CourseDetDTO> getCourses() throws Exception {
        return courseDetDAO.findAll().map(Converter::<CourseDetDTO>getDTOList).get();
    }

    @Override
    public boolean CreateCourse(CourseDetDTO dto) throws Exception {
        return courseDetDAO.save(Converter.getEntity(dto));
    }

    @Override
    public boolean updateCourse(CourseDetDTO dto) throws Exception {
        return courseDetDAO.update(Converter.getEntity(dto));
    }

    @Override
    public boolean deleteCourse(String batchID) throws Exception {
        return courseDetDAO.delete(batchID);
    }

    @Override
    public CourseDetDTO findCourse(String id) throws Exception {
        return courseDetDAO.find(id).map(Converter::<CourseDetDTO>getDTO).orElse(null);
    }
}
