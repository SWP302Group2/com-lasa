
package com.lasa.business.services.implv1;

import com.lasa.business.services.QuestionService;
import com.lasa.data.model.entity.Question;
import com.lasa.data.model.request.QuestionRequestModel;
import com.lasa.data.model.utils.criteria.QuestionSearchCriteria;
import com.lasa.data.model.utils.page.QuestionPage;
import com.lasa.data.model.utils.specification.QuestionSpecification;
import com.lasa.data.model.view.QuestionViewModel;
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
    public Page<QuestionViewModel> findAll(QuestionPage questionPage, QuestionSearchCriteria searchCriteria) {
        Pageable pageable = PageRequest.of(questionPage.getPage(), questionPage.getSize(), Sort.by(questionPage.getOrderBy(), questionPage.getSortBy()));
        return questionRepository
                .findAll(QuestionSpecification.searchSpecification(searchCriteria), pageable)
                .map(t -> new QuestionViewModel(t));
    }

    @Override
    public List<QuestionViewModel> findAll(QuestionSearchCriteria searchCriteria) {
        return questionRepository
                .findAll(QuestionSpecification.searchSpecification(searchCriteria))
                .stream()
                .map(t -> new QuestionViewModel(t))
                .collect(Collectors.toList());
    }

    @Override
    public QuestionViewModel findById(Integer id) {
        Optional<Question> question = questionRepository.findById(id);

        if(question.isPresent())
            return new QuestionViewModel(question.get());

        return null;
    }

    @Override
    public boolean verifyAvailableQuestionForDelete(Integer bookingId, Integer studentId, List<Integer> questionIds) {
        if(questionRepository.countAvailableQuestionsForDelete(questionIds, studentId) == questionIds.size()
        && questionRepository.countByBookingRequestId(bookingId) >= 1)
            return true;

        return false;
    }

    @Override
    public List<QuestionViewModel> createQuestions(List<QuestionRequestModel> questionModels) {
        List<Question> questions = questionModels.stream()
                .map(t -> t.toEntity())
                .collect(Collectors.toList());

        return questionRepository.saveAll(questions)
                .stream()
                .map(t -> new QuestionViewModel(t))
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public List<QuestionViewModel> updateQuestions(List<QuestionRequestModel> questions) {
        
        Set updateId = questions
                .stream()
                .filter(updateQuestion -> updateQuestion.getContent() != null)
                .map(QuestionRequestModel::getId)
                .collect(Collectors.toSet());

        List<Question> questionList = (List<Question>) questionRepository
                .findAllById(updateId)
                .stream()
                .collect(Collectors.toList());

        questionList.forEach((question -> {
            QuestionRequestModel updateQuestion = questions
                    .stream()
                    .filter(t-> t.getId().equals(question.getId()))
                    .findAny()
                    .get();
            question.setContent(updateQuestion.getContent());
        }));

        return questionRepository.saveAll(questionList)
                .stream()
                .map(t -> new QuestionViewModel(t))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteQuestion(List<Integer> ids) {
        questionRepository.deleteAllById(ids);
    }


}
