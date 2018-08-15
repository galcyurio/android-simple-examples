package com.github.galcyurio.textholdermvvm.viewmodel

import com.github.galcyurio.textholdermvvm.misc.LocalRepository
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TextViewModelTest {

    private lateinit var textViewModel: TextViewModel
    @Mock private lateinit var localRepository: LocalRepository

    @Before
    fun setUp() {
        textViewModel = TextViewModel(localRepository)
    }

    @Test
    fun `저장`() {
        textViewModel.save("title", "detail")
        
    }
}