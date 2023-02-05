package ca.josuelubaki.borutoapp.domain.repository

import ca.josuelubaki.borutoapp.domain.model.Hero

interface LocalDataSource {
    suspend fun getSelectHero(heroId : Int) : Hero
}