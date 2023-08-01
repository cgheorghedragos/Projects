package com.ciurezu.gheorghe.dragos.greenlight.repository;

import com.ciurezu.gheorghe.dragos.greenlight.data.entity.UserVoucher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserVoucherRepository extends JpaRepository<UserVoucher, Long> {
    UserVoucher findUserVoucherByUser_UsernameAndVoucher_Id(String username, Long voucherId);

    @Query(value = "SELECT uv.* FROM " +
            "user_voucher uv, users u WHERE " +
            "uv.users_id = u.id AND u.username = ?1 AND " +
            "uv.quantity > 0 ",nativeQuery = true)
    List<UserVoucher> findUserVoucherByUser_UsernameAndQuantityGreaterThan0(String username);
}