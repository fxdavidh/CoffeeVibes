package controller;

import java.util.Vector;

import model.Voucher;
import model.VoucherModel;

public class VoucherDAO {
	
	private VoucherModel vm = new VoucherModel();
	
	public Vector<Voucher> getAllVoucher() {
		// TODO Auto-generated method stub
		Vector<Voucher> vouchers = new Vector<Voucher>();
		vouchers = vm.getAllVoucher();
		return vouchers;
	}
	
	public void insertVoucher(String discount, String status) {
		int tempDiscount = Integer.parseInt(discount);
		if (status.equalsIgnoreCase("Active")) status = "A";
		vm.insertVoucher(tempDiscount, status);
	}
	
	public Voucher getVoucher(int ID) {
		Voucher voucher = vm.getVoucher(ID);
		return voucher;
	}

	public void deleteVoucher(String id) {
		// TODO Auto-generated method stub
		int tempId = Integer.parseInt(id);
		vm.deleteVoucher(tempId);
	}
	
	public void useVoucher(int ID) {
		vm.useVoucher(ID);
	}
}
