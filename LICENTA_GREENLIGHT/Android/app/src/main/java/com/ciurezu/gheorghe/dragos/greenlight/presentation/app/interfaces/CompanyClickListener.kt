package com.ciurezu.gheorghe.dragos.greenlight.presentation.app.interfaces

import com.ciurezu.gheorghe.dragos.greenlight.data.network_model.get_company.CompanyResponse

interface CompanyClickListener {
    fun onClick(companyResponse: CompanyResponse)
}