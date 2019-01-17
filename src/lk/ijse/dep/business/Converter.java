package lk.ijse.dep.business;

import lk.ijse.dep.dao.custom.impl.ParentDetDAOImpl;
import lk.ijse.dep.dto.*;
import lk.ijse.dep.entity.*;

import java.util.List;
import java.util.stream.Collectors;

public class Converter {

    public static <T extends SuperDTO> T getDTO(SuperEntity dto) {
         if (dto instanceof CourseDet) {

            CourseDet c = (CourseDet) dto;
            return (T) new CourseDetDTO(c.getCid(),c.getCname(),c.getDescription(),c.getDuration());

        }else if (dto instanceof BatchDet) {

            BatchDet b = (BatchDet) dto;
            return (T) new BatchDetDTO(b.getCours(),b.getbId(),b.getStartDate(),b.getDescription(),b.getCapacity());

        }else if (dto instanceof StudentDet) {

             StudentDet s = (StudentDet) dto;
             return (T) new StudentDetDTO(s.getsId(),s.getsName(),s.getFullName(),s.getAddress(),s.getCity(),s.getTeleHome(),s.getEmail(),
             s.getDob(),s.getGender(),s.getNic(),s.getSchool(),s.getUni(),s.getFaculty(),s.getTeleMobile(),s.getHigherEd());

         } else if (dto instanceof StudentQualification) {

             StudentQualification sq = (StudentQualification) dto;
             return (T) new StudentQualificationDTO(sq.getQualID(),sq.getQualification(),sq.getInstitute(),sq.getAwardDate(),sq.getSpecialization());

         }else if (dto instanceof ParentDet) {

             ParentDet p = (ParentDet) dto;
             return (T) new ParentDetDTO(p.getStId(),p.getParentName(),p.getParentMobile1(),p.getParentMobile2(),p.getParentMail(),p.getDesignation(),p.getWorkingPlace(),p.getWorkingAddress());

         }  else {
            throw new RuntimeException("This entity can't be converted to a DTO");
        }
    }

    public static <T extends SuperEntity> T getEntity(SuperDTO dto) {
        if (dto instanceof CourseDetDTO) {

            CourseDetDTO c = (CourseDetDTO) dto;
            return (T) new CourseDet(c.getCid(),c.getCname(),c.getDescription(),c.getDuration());

        } else if (dto instanceof BatchDetDTO) {

            BatchDetDTO b = (BatchDetDTO) dto;
            return (T) new BatchDet(b.getCours(),b.getbId(),b.getStartDate(),b.getDescription(),b.getCapacity());

        }else if (dto instanceof StudentDetDTO) {

            StudentDetDTO s = (StudentDetDTO) dto;
            return (T) new StudentDet(s.getsId(),s.getsName(),s.getFullName(),s.getAddress(),s.getCity(),s.getTeleHome(),s.getEmail(),
                    s.getDob(),s.getGender(),s.getNic(),s.getSchool(),s.getUni(),s.getFaculty(),s.getTeleMobile(),s.getHigherEd());

        } else if (dto instanceof StudentQualificationDTO) {

            StudentQualificationDTO sq = (StudentQualificationDTO) dto;
            return (T) new StudentQualification(sq.getQualID(),sq.getStudentId(),sq.getQualification(),sq.getInstitute(),sq.getAwardDate(),sq.getSpecialization());

        }else if (dto instanceof ParentDetDTO) {

            ParentDetDTO p = (ParentDetDTO) dto;
            return (T) new ParentDet(p.getStId(),p.getParentName(),p.getParentMobile1(),p.getParentMobile2(),p.getParentMail(),p.getDesignation(),p.getWorkingPlace(),p.getWorkingAddress());

        } else {
            throw new RuntimeException("This DTO can't be converted to an entity");
        }
    }

    public static <T extends SuperDTO> List<T> getDTOList(List<? extends SuperEntity> entities) {
        return entities.stream().map(Converter::<T>getDTO).collect(Collectors.toList());
    }

    public static <T extends SuperEntity> List<T> getEntityList(List<? extends SuperDTO> dtos) {
        return dtos.stream().map(Converter::<T>getEntity).collect(Collectors.toList());

    }


    public static <T extends SuperDTO> T getDTO(CustomEntity entity, Class<T> dtoClass) {
        if (dtoClass == StudentWithBatchDTO.class) {
            return (T) new StudentWithBatchDTO(entity.getBatch(), entity.getStudent());
        } else {
            throw new RuntimeException("Not Supported DTO");
        }
    }

    public static <T extends SuperDTO> List<T> getDTOList(List<CustomEntity> list, Class<T> dtoClass) {
        return list.stream().map(c -> getDTO(c, dtoClass)).collect(Collectors.toList());
    }
}
