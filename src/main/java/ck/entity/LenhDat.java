package ck.entity;

public class LenhDat {
	private String maCK;
	private String ngayDat; 
	private Character loaiGD;
	private String loaiLenh;
	private Integer soLuong;
	private Double giaDat;
	private String lenh;
	public String getMaCK() {
		return maCK;
	}
	public void setMaCK(String maCK) {
		this.maCK = maCK;
	}
	public String getNgayDat() {
		return ngayDat;
	}
	public void setNgayDat(String ngayDat) {
		this.ngayDat = ngayDat;
	}
	public Character getLoaiGD() {
		return loaiGD;
	}
	public void setLoaiGD(Character loaiGD) {
		this.loaiGD = loaiGD;
	}
	public String getLoaiLenh() {
		return loaiLenh;
	}
	public void setLoaiLenh(String loaiLenh) {
		this.loaiLenh = loaiLenh;
	}
	public Integer getSoLuong() {
		return soLuong;
	}
	public void setSoLuong(Integer soLuong) {
		this.soLuong = soLuong;
	}
	public Double getGiaDat() {
		return giaDat;
	}
	public void setGiaDat(Double giaDat) {
		this.giaDat = giaDat;
	}
	public String getLenh() {
		return lenh;
	}
	public void setLenh(String lenh) {
		this.lenh = lenh;
	}
	public LenhDat(String maCK, String ngayDat, Character loaiGD, String loaiLenh, Integer soLuong, Double giaDat,
			String lenh) {
		super();
		this.maCK = maCK;
		this.ngayDat = ngayDat;
		this.loaiGD = loaiGD;
		this.loaiLenh = loaiLenh;
		this.soLuong = soLuong;
		this.giaDat = giaDat;
		this.lenh = lenh;
	}
	public LenhDat() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
