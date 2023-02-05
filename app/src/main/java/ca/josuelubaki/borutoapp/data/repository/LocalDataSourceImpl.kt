package ca.josuelubaki.borutoapp.data.repository

import ca.josuelubaki.borutoapp.data.local.BorutoDatabase
import ca.josuelubaki.borutoapp.domain.model.Hero
import ca.josuelubaki.borutoapp.domain.repository.LocalDataSource

class LocalDataSourceImpl(private val borutoDatabase: BorutoDatabase): LocalDataSource {
    override suspend fun getSelectHero(heroId: Int): Hero {
           return borutoDatabase.heroDao().getSelectedHero(heroId)
    }
}