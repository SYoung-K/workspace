package vCenter;


import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class vCenterController {	
		
	@Autowired
	vCenterDao dao;
	
	@RequestMapping(value="/vCenter/sido.vCenter", method= {RequestMethod.GET, RequestMethod.POST})
	public void sido(HttpServletRequest req , HttpServletResponse resp) { 

		PrintWriter pw;
		
		List list;
		list = dao.sido();
		
		
		try {
			String result = null;
			   resp.setCharacterEncoding("UTF-8"); 
			   resp.setContentType("text/html; charset=UTF-8");
			   pw = resp.getWriter();
			   for(int i=0;i<list.size();i++) {
				   if(i==0) {result = list.get(i)+",";
				   }
				   else {result += list.get(i)+",";
				   }
			   }
			   pw.print(result);
			   
		   }catch(Exception ex) {
			   ex.printStackTrace();
		   }
	}
	
	
	@RequestMapping(value="/vCenter/sigungu.vCenter", method= {RequestMethod.GET, RequestMethod.POST})
	public void sigungu(HttpServletRequest req , HttpServletResponse resp) { 

		PrintWriter pw;
		
		List list;
		list = dao.sigungu(req.getParameter("sido"));
		
		
		try {
			String result = null;
			resp.setCharacterEncoding("UTF-8"); 
			resp.setContentType("text/html; charset=UTF-8");
			pw = resp.getWriter();
			for(int i=0;i<list.size();i++) {
				if(i==0) {result = list.get(i)+",";
				}
				else {result += list.get(i)+",";
				}
			}
			pw.print(result);
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	@RequestMapping(value="/vCenter/dong.vCenter", method= {RequestMethod.GET, RequestMethod.POST})
	public void dong(vCenterVo vo, HttpServletRequest req , HttpServletResponse resp) { 
		
		PrintWriter pw;
		
		List list;
		list = dao.dong(vo);
		
		
		try {
			String result = null;
			resp.setCharacterEncoding("UTF-8"); 
			resp.setContentType("text/html; charset=UTF-8");
			pw = resp.getWriter();
			for(int i=0;i<list.size();i++) {
				if(i==0) {result = list.get(i)+",";
				}
				else {result += list.get(i)+",";
				}
			}
			pw.print(result);
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	
	  
	  @RequestMapping(value="/vCenter/search.vCenter", method= {RequestMethod.GET, RequestMethod.POST}) 
	  public ModelAndView search(vCenterVo vo) {
	  ModelAndView mv = new ModelAndView();
	  
	  List<vCenterVo> list = null;
		
	  list = dao.search(vo);	 
	  
	  mv.addObject("list",list); 
	  mv.setViewName("vCenterSearch"); 
	 	  
	  return mv; 
	  }
	  
	  
		@RequestMapping(value="/vCenter/information.vCenter", method= {RequestMethod.GET, RequestMethod.POST})
		public void information(vCenterVo vo, HttpServletRequest req , HttpServletResponse resp) { 
			
			PrintWriter pw;			
			
			 String rg[] = req.getParameter("rg").split(" ");
			  vo.setDong(rg[rg.length-1]);    // 해당 읍/면/동
			  vo.setCenterName(req.getParameter("cn"));  // 해당 병원 이름
			  
			  System.out.println(vo.getDong());
			  System.out.println(vo.getCenterName());
			  
			  vCenterVo vo2  = dao.information(vo);    // 검색
			
			try {
				String result = null;
				resp.setCharacterEncoding("UTF-8"); 
				resp.setContentType("text/html; charset=UTF-8");
				pw = resp.getWriter();
				
				result = vo2.getCenterName()+",";
				result += vo2.getFacilityName()+",";
				result += vo2.getPhoneNumber()+",";
				result += vo2.getAddress()+",";
				result += vo2.getLat()+",";
				result += vo2.getLng()+",";						
						
				pw.print(result);
				
			}catch(Exception ex) {
				ex.printStackTrace();
			}
		}

}
