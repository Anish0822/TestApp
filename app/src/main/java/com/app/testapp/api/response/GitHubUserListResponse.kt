package com.app.testapp.api.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class GitHubUserListResponse(
	@field:SerializedName("items")
	val items: List<GitHubUserListResponseItem?>? = null
) : Parcelable

@Parcelize
data class License(
	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("spdx_id")
	val spdxId: String? = null,

	@field:SerializedName("key")
	val key: String? = null,

	@field:SerializedName("url")
	val url: String? = null,

	@field:SerializedName("node_id")
	val nodeId: String? = null
) : Parcelable

@Parcelize
data class GitHubUserListResponseItem(
	@field:SerializedName("allow_forking")
	val allowForking: Boolean? = null,

	@field:SerializedName("stargazers_count")
	val stargazersCount: Int? = null,

	@field:SerializedName("is_template")
	val isTemplate: Boolean? = null,

	@field:SerializedName("pushed_at")
	val pushedAt: String? = null,

	@field:SerializedName("subscription_url")
	val subscriptionUrl: String? = null,

	@field:SerializedName("language")
	val language: String? = null,

	@field:SerializedName("branches_url")
	val branchesUrl: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("license")
	val license: License? = null, // Changed type to `License`

	@field:SerializedName("full_name")
	val fullName: String? = null,

	@field:SerializedName("owner")
	val owner: Owner? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("html_url")
	val htmlUrl: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("updated_at")
	val updatedAt: String? = null
) : Parcelable

@Parcelize
data class Owner(
	@field:SerializedName("login")
	val login: String? = null,

	@field:SerializedName("avatar_url")
	val avatarUrl: String? = null,

	@field:SerializedName("html_url")
	val htmlUrl: String? = null,

	@field:SerializedName("id")
	val id: Int? = null
) : Parcelable
