
package com.lasa.business.services.implv1;

import com.lasa.business.services.QuestionService;
import com.lasa.data.dto.QuestionDTO;
import com.lasa.data.entity.Question;
import com.lasa.data.entity.utils.criteria.QuestionSearchCriteria;
import com.lasa.data.entity.utils.page.QuestionPage;
import com.lasa.data.entity.utils.specification.QuestionSpecification;
import com.lasa.data.repo.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 *
 * @author wifil
 */

@Component
@Qualifier("QuestionServiceImplV1")
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;

    @Autowired
    public QuestionServiceImpl(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Override
    public Page<QuestionDTO> findAll(QuestionPage questionPage, QuestionSearchCriteria searchCriteria) {
        Pageable pageable = PageRequest.of(questionPage.getPage(), questionPage.getSize(), Sort.by(questionPage.getOrderBy(), questionPage.getSortBy()));
        return questionRepository
                .findAll(QuestionSpecification.searchSpecification(searchCriteria), pageable)
                .map(t -> new QuestionDTO(t));
    }

    @Override
    public List<QuestionDTO> findAll(QuestionSearchCriteria searchCriteria) {
        return questionRepository
                .findAll(QuestionSpecification.searchSpecification(searchCriteria))
                .stream()
                .map(t -> new QuestionDTO(t))
                .collect(Collectors.toList());
    }

    @Override
    public QuestionDTO findById(Integer id) {
        Optional<Question> question = questionRepository.findById(id);

        if(question.isPresent())
            return new QuestionDTO(question.get());

        return null;
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
