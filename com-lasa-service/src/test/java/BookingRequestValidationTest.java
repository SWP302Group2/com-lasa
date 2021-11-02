//import com.lasa.Application;
//import com.lasa.business.controllers.implv1.BookingRequestController;
//import com.lasa.data.model.entity.BookingRequest;
//import com.lasa.data.model.entity.Question;
//import com.lasa.data.model.request.BookingRequestRequestModel;
//import org.junit.*;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.validation.BeanPropertyBindingResult;
//import org.springframework.validation.Errors;
//import javax.validation.*;
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.util.Optional;
//import java.util.Set;
//import jakarta.validation.ConstraintViolation;
//import jakarta.validation.Validation;
//import jakarta.validation.Validator;
//
//import jakarta.validation.ValidatorFactory;


//@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT, classes = Application.class)

//public class BookingRequestValidationTest {
////
////    private MockMvc mockMvc;
////
////    private BookingRequestController bookingRequestController;
////
////    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
////
////
////    @Test
////    public void bookingRequestIdIsNull(){
////
////        LocalDateTime now = LocalDateTime.now();
////        BookingRequestRequestModel bookingRequestRequestModel = new BookingRequestRequestModel();
////        BookingRequest bookingRequest = bookingRequestRequestModel.toEntity();
////        bookingRequestRequestModel.setId(null);
////        bookingRequestRequestModel.setStatus(1);
////        bookingRequestRequestModel.setStudentId(12);
////        bookingRequestRequestModel.setTopicId(1);
////        bookingRequestRequestModel.setSlotId(2);
////        bookingRequestRequestModel.setTitle("i need help");
////        bookingRequestRequestModel.setCreateTime(now);
////
////        Set<ConstraintViolation<BookingRequest>> constraintViolations =
////                validator.validate(bookingRequest);
////
////        Assert.assertEquals( constraintViolations.equals("must not be null"));
////
//////        Assert.assertEquals(1,constraintViolations.size());
////    }
//   ;
//    private static Validator validator;
//
//    @BeforeClass
//    public static void createValidator() {
//        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
//        validator = validatorFactory.getValidator();
//    }
//    @Test
//    public void shouldReturnViolation() {
//
//                LocalDateTime now = LocalDateTime.now();
////        bookingRequestRequestModel.setId(10);
////        bookingRequestRequestModel.setStatus(1);
////        bookingRequestRequestModel.setStudentId(12);
////        bookingRequestRequestModel.setTopicId(1);
////        bookingRequestRequestModel.setSlotId(2);
////        bookingRequestRequestModel.setTitle("i need help");
////        bookingRequestRequestModel.setCreateTime(now);
////        bookingRequestRequestModel.setRating(2);
////        bookingRequestRequestModel.setQuestions(null);
//        BookingRequestRequestModel bookingRequestRequestModel = new BookingRequestRequestModel(10, 12, 1, null, 2, 2, "help", 1, now);
//        Set<ConstraintViolation<BookingRequestRequestModel>> violations =
//                validator.validate(bookingRequestRequestModel);
//        Assert.assertTrue(violations.isEmpty());
//        Assert.assertTrue(violations.size() == 0);
//    }
//
////    private Validator validator;
////
////    private void validateBean(Object bean) throws AssertionError {
////        Optional<ConstraintViolation<Object>> violation = validator.validate(bean).stream().findFirst();
////        if (violation.isPresent()) {
////            throw new ValidationException(violation.get().getMessage());
////        }
////    }
////
////    @Before
////    public void setUp() {
////        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
////        validator = factory.getValidator();
////    }
////
////    @Test(expected = ValidationException.class)
////    public void testValidationWhenNoNameThenThrowException() {
////        validateBean(new BookingRequestRequestModel().setRating(1));
////    }
//@Mock
//private NameMatch nameMatch;
//
//    @Mock
//    private ConstraintValidatorContext constraintValidatorContext;
//
//    @Test
//    public void testIsValid() {
//
//        when(nameMatch.first()).thenReturn("firstname");
//        when(nameMatch.second()).thenReturn("lastname");
//
//
//        NameValidator nameValidator = new NameValidator();
//        nameValidator.initialize(nameMatch);
//
//        User user = new User();
//        user.setFirstname("Duke");
//        user.setLastname("Duke");
//
//        boolean result = nameValidator.isValid(user, constraintValidatorContext);
//        assertTrue(result);
//    }
//}
