/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.business.services.implv1;

import com.lasa.business.config.utils.StudentStatus;
import com.lasa.business.services.StudentService;
import com.lasa.data.model.entity.FavoriteLecturer;
import com.lasa.data.model.entity.Lecturer;
import com.lasa.data.model.entity.Student;
import com.lasa.data.model.request.StudentRequestModel;
import com.lasa.data.model.utils.criteria.StudentSearchCriteria;
import com.lasa.data.model.utils.page.StudentPage;
import com.lasa.data.model.utils.specification.StudentSpecification;
import com.lasa.data.model.view.StudentViewModel;
import com.lasa.data.repo.repository.FavoriteLecturerRepository;
import com.lasa.data.repo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 *
 * @author hai
 */
@Component
@Qualifier("StudentServiceImplV1")
public class StudentServiceImpl implements StudentService {
    
    private final StudentRepository studentRepository;
    private final FavoriteLecturerRepository favoriteLecturerRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository, FavoriteLecturerRepository favoriteLecturerRepository) {
        this.studentRepository = studentRepository;
        this.favoriteLecturerRepository = favoriteLecturerRepository;
    }

    @Override
    public Page<StudentViewModel> findWithArgument(StudentSearchCriteria searchCriteria, StudentPage studentPage) {
        Pageable pageable = PageRequest.of(studentPage.getPage(), studentPage.getSize(), Sort.by(studentPage.getOrderBy(), studentPage.getSortBy()));
        return studentRepository
                .findAll(StudentSpecification.searchSpecification(searchCriteria), pageable)
                .map(t -> new StudentViewModel(t));
    }

    @Override
    public List<StudentViewModel> findWithArgument(StudentSearchCriteria searchCriteria) {
        return studentRepository
                .findAll(StudentSpecification.searchSpecification(searchCriteria))
                .stream()
                .map(t -> new StudentViewModel(t))
                .collect(Collectors.toList());
    }

    @Override
    public StudentViewModel findByStudentId(Integer id) {
        Optional<Student> student = studentRepository.findById(id);
        if(student.isPresent())
            return new StudentViewModel(student.get());
        return null;
    }

    @Override
    public StudentViewModel createStudent(StudentRequestModel student) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean verifyStudent(List<Integer> ids) {
        return studentRepository.countAvailableForDelete(ids) == ids.size();
    }

    @Override
    @Transactional
    public StudentViewModel updateStudent(StudentRequestModel model) {
        
        Student student = studentRepository.findById(model.getId()).get();
        
        if(model.getMssv() != null)
            student.setMssv(model.getMssv());
        
        if(model.getMajorId() != null)
            student.setMajorId(model.getMajorId());
        
        if(model.getName() != null)
            student.setName(model.getName());
        
        if(model.getPhone() != null)
            student.setPhone(model.getPhone());
        
        if(model.getBirthday() != null)
            student.setBirthday(model.getBirthday());
        
        if(model.getGender() != null)
            student.setGender(model.getGender());
        
        if(model.getAddress() != null)
            student.setAddress(model.getAddress());

        if(Objects.nonNull(model.getStatus()))
            student.setStatus(model.getStatus());

        if(Objects.nonNull(model.getAvatarUrl()))
            student.setAvatarUrl(model.getAvatarUrl());

        if(Objects.nonNull(model.getLecturers())) {
            List<FavoriteLecturer> favoriteLecturers = model.getLecturers()
                    .stream()
                    .map(t -> FavoriteLecturer.builder()
                            .lecturer(Lecturer.builder()
                                    .id(t)
                                    .build())
                            .student(Student.builder()
                                    .id(model.getId())
                                    .build())
                            .build())
                    .collect(Collectors.toList());

            favoriteLecturerRepository.deleteAllByStudentId(model.getId());
            favoriteLecturerRepository.saveAll(favoriteLecturers);
        }

        return new StudentViewModel(studentRepository.save(student));
    }

    @Override
    @Transactional
    public void deleteStudents(List<Integer> ids) {
        List<Student> students = studentRepository.findAllById(ids);
        students.stream()
                .forEach(t -> t.setStatus(StudentStatus.DELETED.getCode()));
        studentRepository.saveAll(students);
    }
    
}
