package com.ciurezu.gheorghe.dragos.greenlight.repository;

import com.ciurezu.gheorghe.dragos.greenlight.data.entity.Voucher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.annotation.Nullable;
import javax.validation.constraints.Null;
import java.util.List;

public interface VoucherRepository extends JpaRepository<Voucher, Long> {
    @Query(value = "SELECT * FROM voucher WHERE id=?1 ", nativeQuery = true)
    Voucher findByVoucherId(Long id);

    @Query(value = "SELECT * FROM voucher WHERE quantity > 0",nativeQuery = true)
    List<Voucher> findAllActiveVouchers();
}