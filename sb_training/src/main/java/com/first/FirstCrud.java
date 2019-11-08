package com.first;

import java.text.ParseException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/firstCrud", method = RequestMethod.GET)
public class FirstCrud {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	FirstCrudSvc firstCrudSvc;

	@GetMapping("/add")
	public ModelAndView init() throws ParseException {
		return new ModelAndView("firstCrudAdd");
	}

	@PostMapping("/ins")
	@ResponseBody
	public FirstCrudVo add(Model model, @ModelAttribute("firstCrudVo") FirstCrudVo firstCrudVo,
			HttpServletRequest request) throws ParseException {
		logger.info("/ins");
		logger.info("1=" + firstCrudVo.getName());
		logger.info("1.5=" + firstCrudVo);
		logger.info("2=" + request.getParameter("name"));
		logger.info((String) model.getAttribute("name"));
		firstCrudVo.setName("xxx");
		FirstCrudVo firstCrudVo1 = new FirstCrudVo();
		firstCrudVo1.setName("xxx");
		return firstCrudVo1;
	}

	@PostMapping("/insToDb")
	@ResponseBody
	public ComPo addToDb(Model model, @ModelAttribute("firstCrudVo") FirstCrudVo firstCrudVo,
			HttpServletRequest request) throws ParseException {
		logger.info("/insToDb");
		logger.info("1=" + firstCrudVo.getName());
		logger.info("2=" + request.getParameter("name"));
		logger.info((String) model.getAttribute("name"));
		logger.info("3=" + request.getParameter("sex"));
		return firstCrudSvc.ins(firstCrudVo, request);
	}

	@GetMapping("/qry")
	public ModelAndView firstSel() throws ParseException {
		return new ModelAndView("firstCrud");
	}

	@PostMapping("/sel")
	@ResponseBody
	public ComPo sel(Model model, @ModelAttribute("firstCrudVo") FirstCrudVo firstCrudVo, HttpServletRequest request)
			throws ParseException {
		logger.info("/sel");
		logger.info("1=" + firstCrudVo.getName());
		logger.info("2=" + firstCrudVo.getSex());
		logger.info("3=" + request.getParameter("name"));
		logger.info("4=" + request.getParameter("sex"));
		logger.info((String) model.getAttribute("name"));
		return firstCrudSvc.sel(firstCrudVo);
	}

	@GetMapping("/edit/{sbtId}")
	public ModelAndView edit(@PathVariable String sbtId, Model model) throws ParseException {
		logger.info("sbtId=" + sbtId);
		ComPo comOutVo = firstCrudSvc.sel(new FirstCrudVo(sbtId));
		Map<String, Object> rowMap = comOutVo.getRowList().get(0);
		logger.info("NAME=" + rowMap.get("NAME"));
		logger.info("SEX=" + rowMap.get("SEX"));
		// we could have auto mapping function
		model.addAttribute("id", rowMap.get("ID"));
		model.addAttribute("name", rowMap.get("NAME"));
		model.addAttribute("sex", rowMap.get("SEX"));
		return new ModelAndView("/firstCrudEdit");
	}

	@PostMapping("/upd")
	@ResponseBody
	public ComPo upd(@ModelAttribute("firstCrudVo") FirstCrudVo firstCrudVo, HttpServletRequest request) {
		logger.info("1=" + firstCrudVo.getId());
		logger.info("2=" + firstCrudVo.getName());
		logger.info("3=" + firstCrudVo.getSex());
		return firstCrudSvc.upd(firstCrudVo, request);
	}

	@PostMapping("/del/{idList}")
	@ResponseBody
	public ComPo del(@PathVariable String idList, @ModelAttribute("firstCrudVo") FirstCrudVo firstCrudVo) {
		logger.info("/empl/del: s");
		logger.info("emplVo.getIdList=" + firstCrudVo.getIdList());
		return firstCrudSvc.del(firstCrudVo);
	}

}
