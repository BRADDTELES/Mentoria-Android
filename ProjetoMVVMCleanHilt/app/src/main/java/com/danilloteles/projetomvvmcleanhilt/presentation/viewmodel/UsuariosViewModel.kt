package com.danilloteles.projetomvvmcleanhilt.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.danilloteles.projetomvvmcleanhilt.domain.usecase.GetUsuariosUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UsuariosViewModel @Inject constructor(
    private val usuariosUseCase: GetUsuariosUseCase
) : ViewModel() {



}