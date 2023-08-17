package com.mskill.ExamPortal.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import com.mskill.ExamPortal.exception.ResourceNotFoundException;
import com.mskill.ExamPortal.model.Moderator;
import com.mskill.ExamPortal.JDBCRepository.ModeratorJDBCRepository;
import com.mskill.ExamPortal.JPARepository.ModeratorJPARepository;
import com.mskill.ExamPortal.response.ModeratorResponse;
import com.mskill.ExamPortal.response.FacultyResponse;



@Service
@Transactional
public class ModeratorService {
	
	@Autowired
	 private ModeratorJPARepository moderatorRepository;
	
	@Autowired
	 private ModeratorJDBCRepository moderatorJDBC;
	
	
	
	/*public List<ModeratorResponse> getAllMod() {
        List<Moderator> mod =  moderatorRepository.findAll();
        
        List<ModeratorResponse> modResponse = new ArrayList<>();
        
        
        for (int i = 0; i < mod.size(); i++) {
         ModeratorResponse modView = new ModeratorResponse();
         modView.setModerator_id(mod.get(i).getModerator_id());
         modView.setSalutation(mod.get(i).getSalutation());
         modView.setFirst_name(mod.get(i).getFirst_name());
         modView.setLast_name(mod.get(i).getLast_name());
         modView.setModerator_email(mod.get(i).getModerator_email());
         modView.setMobile(mod.get(i).getMobile());
         modView.setCollege_id(mod.get(i).getCollege_id());
         modView.setSubject_id(mod.get(i).getSubject_id());
         //modView.setSubject_name(mod.get(i).getSubject_name());
   		 modResponse.add(modView);
        }
        
        return modResponse;
        
    }*/
	public List<ModeratorResponse> getAllMod() {
		List<ModeratorResponse> mod = moderatorJDBC.findAllModView();
		return mod;
	}
 
 /*public ModeratorResponse getModById(int moderator_id) throws ResourceNotFoundException{
	 Moderator mod = moderatorRepository.findById(moderator_id).orElseThrow(() -> new ResourceNotFoundException("Moderator not found for this id :: " + moderator_id));
	 
	 ModeratorResponse modView = new ModeratorResponse();
	 modView.setModerator_id(mod.getModerator_id());
     modView.setSalutation(mod.getSalutation());
     modView.setFirst_name(mod.getFirst_name());
     modView.setLast_name(mod.getLast_name());
     modView.setModerator_email(mod.getModerator_email());
     modView.setMobile(mod.getMobile());
     modView.setCollege_id(mod.getCollege_id());
     modView.setSubject_id(mod.getSubject_id());
     //modView.setSubject_name(mod.getSubject_name());
	 
	 return modView;         
 }*/
	
	public ModeratorResponse getModById(long moderator_id) throws ResourceNotFoundException{
		ModeratorResponse mod = moderatorJDBC.findModViewById(moderator_id)
				.orElseThrow(() -> new ResourceNotFoundException("Moderator not found for this id :: " + moderator_id));
		 return mod;
	}
	
	/*public FacultyResponse getExaminerById(long subject_id) throws ResourceNotFoundException{
		FacultyResponse examiner = (moderatorJDBC.findExaminerBySubId(subject_id))
				.orElseThrow(() -> new ResourceNotFoundException("Examiner not found for this Subject :: " + subject_id));
		 return examiner;
	}*/
 
 public Moderator createModerator(@RequestBody Moderator mod) {
        return moderatorRepository.save(mod);
    }

public List<ModeratorResponse> findAllModView() {
	 List<ModeratorResponse> moderator =  moderatorJDBC.findAllModView();
     return moderator;
}

public List<ModeratorResponse> findAllExaminer() {
	 List<ModeratorResponse> examiner =  moderatorJDBC.findAllExaminer();
    return examiner;
}

public List GenerateRandomExaminerId() {
	List randomExaminer = moderatorJDBC.GenerateRandomExaminerId();
	return randomExaminer;
}

public List<Integer> setFacultyToAnswer(long subid){
	List<Integer> evaluatorId = moderatorJDBC.fetchAllFacultyIdView(subid);
	List<Integer> answerId = moderatorJDBC.fetchAllAnswerId();
	
	List<Integer> resultSet = new ArrayList<Integer>();
    short asncount=(short) answerId.size();
    short evalcount=(short) evaluatorId.size();
	if(asncount > 0 && evalcount > 0 ){
		if(asncount>=evalcount) {	// If Number of Answers Greater Than to Number of Evaluator
			for(int outer=0;outer<asncount;) {
				for(int inner=0;inner<evalcount;inner++) {
					if(outer<asncount)
					{
						resultSet.add(moderatorJDBC.updateFacultyAnswer(evaluatorId.get(inner), answerId.get(outer)));
					}
					outer++;
				}
			}
		}else if(asncount<evaluatorId.size()) {	// If Number of Answers Less Than to Number of Evaluator
			for(int outer=0;outer<evalcount;) {
				for(int inner=0;inner<evalcount;inner++) {
					if(outer<asncount)
					{
						resultSet.add(moderatorJDBC.updateFacultyAnswer(evaluatorId.get(inner), answerId.get(outer)));
						outer++;
					}
				}
			}
		}else {		//If Number of Answers are Equal to Number of Evaluator
			for(int outer=0;outer<evalcount;outer++) {
				resultSet.add(moderatorJDBC.updateFacultyAnswer(evaluatorId.get(outer), answerId.get(outer)));
			}
		}
		return resultSet;
	}else {return null;}
}



}
