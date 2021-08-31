package com.pluralsight.conference.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pluralsight.conference.model.Course;
import com.pluralsight.conference.model.Registration;
import com.pluralsight.conference.model.RegistrationReport;
import com.pluralsight.conference.repository.CourseRepository;
import com.pluralsight.conference.repository.RegistrationRepository;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    @Autowired
    private RegistrationRepository registrationRepository;
    @Autowired
    private CourseRepository courseRepository;


    @Override
    @Transactional
    public Registration addRegistration(Registration registration) {
		/**
		 * @requirement - whoever registering as new, should attend the 'Intro' Course
		 * 
		 */
        registration = registrationRepository.save(registration);
		System.out.println(registration);
		// if (registration.getId() == null) {
			Course course = new Course();
			course.setName("Intro");
			course.setDescription("Every attendee must comple the intro.");
			course.setRegistration(registration);

			courseRepository.save(course);
			// }

        return registration;
    }

    @Override
    public List<Registration> findAll() {
        return registrationRepository.findAll();
    }

    @Override
    public List<RegistrationReport> findAllReports() {
		return registrationRepository.registrationReport(); // .findAllReports();
    }

}
