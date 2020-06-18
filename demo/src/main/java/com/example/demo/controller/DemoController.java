package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Book;
import com.example.demo.exception.ElementNotFoundException;
import com.example.demo.quiz.Question;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.QuestionRepository;

//CORS 설정
// - Cross - origin resource sharing 
// - 다른 출처의 자원에 접근할 수 있도록 허용하는 설정 
//@CrossOrigin(origins = "*",allowedHeaders = {"A"})
@CrossOrigin(origins = "http://localhost:8000")
@RestController
public class DemoController {

	@GetMapping("/")
	public String welcome() {
		return "welcome to rest service";
	}

	// Rest 에서의 핵심은 자원을 기준으로 주소 정한다.
	// 추가적으로 get(r)/post(c)/put(u)/ delete(d) 방식을 더해서 직관적인 처리를 수행

	// soap 에서의 핵심은 서비스를 기준으로

	// book 으로 설계를 하면
	// [post]/book/ : 등록
	// [get]/book/ : 조회
	// [put]/book/ : 수정
	// [delete]/book/ : 삭제

	// put , post 는 body를 가질 수 있다.

	@Autowired
	private BookRepository bookRepo;
	private QuestionRepository questionRepo;

	// @requestParam : 파라미ㅏ터 수신
	// @ModelAttribute : 파라미터를 객체로 수신
	// @requestBody : 요청 바디를 수신
	// - 등록후에는 등록된 결과를 반환 (json 형식 : jackson-databind 사용)

	@PostMapping("/book/")
	public Book add(@RequestBody Book book) {
		return bookRepo.save(book);

	}

	@GetMapping("/book/")
	public List<Book> list() {
		return bookRepo.findAll();// 단순 목록

	}

	// 파라미터 처럼 주소에 데이터를 보낼일이 있으면 pathvariable 을 사용
	@DeleteMapping("/book/{no}")
	public void delete(@PathVariable int no) {
		bookRepo.deleteById(no);
	}

	// 수정을 위해서는 수정할 항목과 pk가 필요
	@PutMapping("/book/")
	public Book edit(@RequestBody Book book) throws Exception {
//		//선 조회 + 후 수정
//		Optional<Book> op = bookRepo.findById(book.getNo());
//		Book find = op.orElseThrow(Exception::new);

		Book find = bookRepo.findById(book.getNo()).orElseThrow(Exception::new);
		// find 에 수정된 정보를 추가하여 저장하면 수정이 이루어진다 .
		find.setName(book.getName());
		find.setPublisher(book.getPublisher());
		find.setPrice(book.getPrice());

		return bookRepo.save(find);

	}

	// 단일 조회
	@GetMapping("/book/no/{no}")
	public Book get(@PathVariable int no) {
		return bookRepo.findById(no).orElseThrow(ElementNotFoundException::new);
	}

	@GetMapping("/book/name/{name}")
	public List<Book> get(@PathVariable String name) {
		return bookRepo.findAllByNameContainingIgnoreCaseOrderByNameAsc(name); // 이름 일치 검색

		
	}
	
	//  /book/page/1/size/20
	@GetMapping("/book/page/{page}/size/{size}/order/{order}/name/{name}")
	public Page<Book> list(@PathVariable int page, @PathVariable int size, @PathVariable String order, @PathVariable String name){
		
		//page 번호는 0부터
		PageRequest req = PageRequest.of(page-1, size, Sort.by(order));
//		return bookRepo.findAll(req);
		
		return bookRepo.findAllByName(name,req);
		
		
		
	
	}
	

}