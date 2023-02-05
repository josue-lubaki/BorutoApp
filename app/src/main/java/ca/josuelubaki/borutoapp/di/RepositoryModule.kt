package ca.josuelubaki.borutoapp.di

import android.content.Context
import ca.josuelubaki.borutoapp.data.repository.DataStoreOperationsImpl
import ca.josuelubaki.borutoapp.data.repository.Repository
import ca.josuelubaki.borutoapp.domain.repository.DataStoreOperations
import ca.josuelubaki.borutoapp.domain.use_case.UseCases
import ca.josuelubaki.borutoapp.domain.use_case.get_all_heroes.GetAllHeroesUseCase
import ca.josuelubaki.borutoapp.domain.use_case.get_selected_hero.GetSelectedHeroUseCase
import ca.josuelubaki.borutoapp.domain.use_case.read_onboarding.ReadOnBoardingUseCase
import ca.josuelubaki.borutoapp.domain.use_case.save_onboarding.SaveOnBoardingUseCase
import ca.josuelubaki.borutoapp.domain.use_case.search_heroes.SearchHeroesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideDataStoreOperations(@ApplicationContext context : Context) : DataStoreOperations {
        return DataStoreOperationsImpl(context = context)
    }

    @Provides
    @Singleton
    fun provideUseCases(repository: Repository) : UseCases {
        return UseCases(
            readOnBoardingUseCase = ReadOnBoardingUseCase(repository),
            saveOnBoardingUseCase = SaveOnBoardingUseCase(repository),
            getAllHeroesUseCase = GetAllHeroesUseCase(repository),
            searchHeroesUseCase = SearchHeroesUseCase(repository),
            getSelectedHeroUseCase = GetSelectedHeroUseCase(repository)
        )
    }
}