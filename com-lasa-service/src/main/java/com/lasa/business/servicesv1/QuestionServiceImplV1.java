
package com.lasa.business.servicesv1;

import com.lasa.data.entity.Question;
import com.lasa.data.entity.utils.criteria.QuestionSearchCriteria;
import com.lasa.data.entity.utils.page.QuestionPage;
import com.lasa.data.entity.utils.specification.QuestionSpecification;
import com.lasa.data.repository.QuestionRepository;
import java.util.List;
import java.util.Set;

import com.lasa.business.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author wifil
 */

@Component
@Qualifier("QuestionServiceImplV1")
public class QuestionServiceImplV1 implements QuestionService {

    private final QuestionRepository questionRepository;

    @Autowired
    public QuestionServiceImplV1(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Override
    public Page<Question> findAll(QuestionPage questionPage, QuestionSearchCriteria searchCriteria) {
        Pageable pageable = PageRequest.of(questionPage.getPage(), questionPage.getSize(), Sort.by(questionPage.getOrderBy(), questionPage.getSortBy()));
        return questionRepository.findAll(QuestionSpecification.searchSpecification(searchCriteria), pageable);
    }

    @Override
    public Question findById(Integer id) {
        return questionRepository.findById(id).orElse(null);
    }

    @Override
    public List<Question> createQuestions(List<Question> questions) {
        return questionRepository.saveAll(questions);
    }

    @Transactional
    @Override
    public List<Question> updateQuestions(List<Question> questions) {
        
        Set updateId = questions
                .stream()
                .filter(updateQuestion -> updateQuestion.getContent() != null)
                .map(Question::getId)
                .collect(Collectors.toSet());

        List<Question> questionList = (List<Question>) questionRepository
                .findAllById(updateId)
                .stream()
                .collect(Collectors.toList());

        questionList.forEach((question -> {
            Question updateQuestion = questions
                    .stream()
                    .filter(t-> t.getId().equals(question.getId()))
                    .findAny()
                    .get();
            
            question.setContent(updateQuestion.getContent());
        }));

        return questionRepository.saveAll(questionList);
    }

    @Override
    public void deleteQuestion(List<Integer> ids) {
        questionRepository.deleteAllById(ids);
    }
}
