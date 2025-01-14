/*
 * Designed and developed by 2020 skydoves (Jaewoong Eum)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.skydoves.disneymotions.view.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.switchMap
import com.skydoves.disneymotions.base.LiveCoroutinesViewModel
import com.skydoves.disneymotions.model.Poster
import com.skydoves.disneymotions.repository.DetailRepository

class PosterDetailViewModel(
  private val repository: DetailRepository
) : LiveCoroutinesViewModel() {

  private val posterIdLiveData: MutableLiveData<Long> = MutableLiveData()
  val poster: LiveData<Poster>

  init {
    poster = posterIdLiveData.switchMap {
      launchOnViewModelScope {
        repository.getPosterById(it).asLiveData()
      }
    }
  }

  fun getPoster(id: Long) = apply {
    posterIdLiveData.postValue(id)
  }
}
