package shop.mtcoding.ajax.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import shop.mtcoding.ajax.dto.TechResponse;
import shop.mtcoding.ajax.model.Category;
import shop.mtcoding.ajax.model.CategoryRepository;
import shop.mtcoding.ajax.model.Tech;
import shop.mtcoding.ajax.model.TechRepository;

@Controller
public class TechController {

    @Autowired
    private TechRepository techRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/v1/test/tech")
    public @ResponseBody TechResponse.MainDTO techV1() {
        List<Category> categoryList = categoryRepository.findAll();
        List<Tech> techList = techRepository.findByCategoryId(1);
        System.out.println("=================================");
        TechResponse.MainDTO mainDTO = new TechResponse.MainDTO(categoryList, techList);
        return mainDTO; // messageconverter 발동 - json 직렬화. 이 때 LAZY 발동한다.
        // return이 DTO일 때에는 LAZY로딩이 발생하지 않는다. 영속화 되어있지 않기 때문에.
        // 영속화는 db에서 조회했을 때 발생하는데(Entity), DTO는 Entity를 깊은 복사한 것이기 때문에
        // 영속화 될 수가 없다.
        // messageconverger가 getter 다때려버린다. 언제? 어디서?

        // lazy로딩 일어나지 않게 하는법 2가지
        // 1. Entity의 연관된 객체에 @JsonIgnore
        // 2. DTO로 복사해서 return하기
    }

    @GetMapping("/v2/test/tech")
    public @ResponseBody List<Tech> techV2() {
        List<Tech> techList = techRepository.findByCategoryId(1);
        return techList;
    }

    // 1. 빈 껍데기 디자인을 준다. (데이터 없음)
    @GetMapping("/tech")
    public String tech() {
        return "main";
    }

    @GetMapping("/api/category")
    public @ResponseBody List<Category> categoryApi() {
        return categoryRepository.findAll();
    }

    @GetMapping("/api/tech")
    public @ResponseBody List<Tech> techApi(@RequestParam(defaultValue = "1") Integer categoryId) {
        return techRepository.findByCategoryId(categoryId);
    }

}
