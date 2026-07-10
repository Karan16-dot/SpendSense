package com.spendsense.income.service;

import com.spendsense.common.exception.ResourceNotFoundException;
import com.spendsense.common.security.CurrentUserService;
import com.spendsense.income.dto.request.CreateIncomeRequest;
import com.spendsense.income.dto.request.UpdateIncomeRequest;
import com.spendsense.income.dto.response.IncomeResponse;
import com.spendsense.income.entity.Income;
import com.spendsense.income.mapper.IncomeMapper;
import com.spendsense.income.repository.IncomeRepository;
import com.spendsense.income.validator.IncomeValidator;
import com.spendsense.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class IncomeServiceImpl implements IncomeService {

    private final IncomeRepository incomeRepository;
    private final IncomeMapper incomeMapper;
    private final IncomeValidator incomeValidator;
    private final CurrentUserService currentUserService;

    @Override
    public IncomeResponse createIncome(CreateIncomeRequest request) {

        incomeValidator.validate(request);

        User currentUser = currentUserService.getCurrentUser();

        Income income = incomeMapper.toEntity(request);

        income.setUser(currentUser);

        Income savedIncome = incomeRepository.save(income);

        return incomeMapper.toResponse(savedIncome);
    }

    @Override
    @Transactional(readOnly = true)
    public IncomeResponse getIncome(UUID incomeId) {

        User currentUser = currentUserService.getCurrentUser();

        Income income = incomeRepository
                .findByIdAndUserAndDeletedFalse(incomeId, currentUser)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Income not found."));

        return incomeMapper.toResponse(income);
    }

    @Override
    public IncomeResponse updateIncome(UUID incomeId, UpdateIncomeRequest request) {

        User currentUser = currentUserService.getCurrentUser();

        Income income = incomeRepository
                .findByIdAndUserAndDeletedFalse(incomeId, currentUser)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Income not found."));

        incomeMapper.updateEntity(request, income);

        Income updatedIncome = incomeRepository.save(income);

        return incomeMapper.toResponse(updatedIncome);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<IncomeResponse> getAllIncome(Pageable pageable) {

        User currentUser = currentUserService.getCurrentUser();

        return incomeRepository.findByUserAndDeletedFalse(currentUser, pageable)
                .map(incomeMapper::toResponse);
    }

    @Override
    public void deleteIncome(UUID incomeId) {

        User currentUser = currentUserService.getCurrentUser();

        Income income = incomeRepository
                .findByIdAndUserAndDeletedFalse(incomeId, currentUser)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Income not found."));

        income.setDeleted(true);

        incomeRepository.save(income);
    }
}