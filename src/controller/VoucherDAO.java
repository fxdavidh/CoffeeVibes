package controller;

import model.Voucher;
import model.VoucherModel;

public class VoucherDAO {
	
	private VoucherModel vm = new VoucherModel();
	
	public Voucher getVoucher(int ID) {
		Voucher voucher = vm.getVoucher(ID);
		return voucher;
	}
	
	public void useVoucher(int ID) {
		vm.useVoucher(ID);
	}
}
