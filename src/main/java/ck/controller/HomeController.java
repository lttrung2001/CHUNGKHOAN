package ck.controller;

import java.sql.CallableStatement;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ck.entity.LenhDat;

@Controller
public class HomeController {
	private String getToday() {
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");  
	    Date date = new Date();  
	    return formatter.format(date);  
	}
	@RequestMapping(value = "index", method = RequestMethod.GET)
	public String index(ModelMap model) {
		LenhDat lenhDat = new LenhDat();
		lenhDat.setLoaiGD('M');
		model.addAttribute("lenhDat", lenhDat);
		return "index";
	}
	@RequestMapping(value = "index", method = RequestMethod.POST)
	public String index(ModelMap model, @ModelAttribute("lenhDat") LenhDat lenhDat, BindingResult errors) throws SQLException {
//		Date now = new Date();
//		Date gioBatDau = new Date();
//		gioBatDau.setHours(9);
//		gioBatDau.setMinutes(0);
//		gioBatDau.setSeconds(0);
//		if (now.before(gioBatDau)) {
//			model.addAttribute("HEAD_MESSAGE", "Sàn chưa mở");
//			model.addAttribute("lenhDat", lenhDat);
//			return "index";
//		}
		if (lenhDat.getMaCK().isBlank()) {
			errors.rejectValue("maCK", "lenhDat", "Không được để trống!");
		}
		else if (lenhDat.getMaCK().length() != 3) {
			errors.rejectValue("maCK", "lenhDat", "Vui lòng nhập đúng 3 ký tự!");
		}
		else if (!lenhDat.getMaCK().chars().allMatch(c-> (c>=65 && c<=90) || (c>='0' && c<='9'))) {
			errors.rejectValue("maCK", "lenhDat", "Chỉ bao gồm chữ cái in hoa và số!");
		}
		if (lenhDat.getSoLuong() == null) {
			errors.rejectValue("soLuong", "lenhDat", "Không được để trống!");
		}
		else if (lenhDat.getSoLuong() % 10 != 0) {
			errors.rejectValue("soLuong", "lenhDat", "Khối lượng phải chia hết cho 10!");
		}
		if (lenhDat.getGiaDat() == null) {
			errors.rejectValue("giaDat", "lenhDat", "Không được để trống!");
		}
		if (errors.hasErrors()) {
			model.addAttribute("HEAD_MESSAGE", "Vui lòng sửa các lỗi bên dưới");
			model.addAttribute("lenhDat", lenhDat);
			model.addAttribute("trangThaiDatClass", "head-msg-fail");
			return "index";
		}
		lenhDat.setNgayDat(getToday());
		String dbURL = "jdbc:sqlserver://localhost\\SERVER0:1434; Database=CHUNGKHOAN";
        String user = "sa";
        String pass = "tt";
        DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());
        try {
        	Connection connection = DriverManager.getConnection(dbURL,user,pass);
        	// CACH 1
            CallableStatement statement = connection.prepareCall("{CALL SP_KHOPLENH_LO(?,?,?,?,?)}");
            statement.setString(1, lenhDat.getMaCK());
            statement.setString(2, lenhDat.getNgayDat());
            statement.setString(3, lenhDat.getLoaiGD().toString());
            statement.setInt(4, lenhDat.getSoLuong());
            statement.setDouble(5, lenhDat.getGiaDat());
            statement.execute();
        	
        	// CACH 2
//        	Statement statement = connection.createStatement();
//        	String sql = String.format("INSERT INTO LENHDAT (MACP,NGAYDAT,LOAIGD,LOAILENH,SOLUONG,GIADAT,TRANGTHAILENH) "
//        				+ "VALUES ('%s',getdate(),'%c','%s',%d,%f,N'Chờ khớp')",lenhDat.getMaCK(),
//        																		lenhDat.getLoaiGD(),
//        																		lenhDat.getLenh(),
//        																		lenhDat.getSoLuong(),
//        																		lenhDat.getGiaDat());
//        	statement.executeUpdate(sql);
            connection.close();
            model.addAttribute("HEAD_MESSAGE", "Đặt lệnh thành công!");
            model.addAttribute("trangThaiDatClass", "head-msg-success");
        }
        catch (SQLException e) {
            e.printStackTrace();
            model.addAttribute("HEAD_MESSAGE", "Đặt lệnh thất bại!");
            model.addAttribute("trangThaiDatClass", "head-msg-fail");
        }
        model.addAttribute("lenhDat", lenhDat);
        return "index";
	}
}
