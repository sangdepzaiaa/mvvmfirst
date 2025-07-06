package com.example.myapplication.upsplash.data.response


import com.squareup.moshi.Json


  data class CollectionsItemsRp(
    @Json(name = "id") val id: String,
    @Json(name = "title") val title: String,
    @Json(name = "description") val description: String?,
    @Json(name = "published_at") val publishedAt: String,
    @Json(name = "last_collected_at") val lastCollectedAt: String,
    @Json(name = "updated_at") val updatedAt: String,
    @Json(name = "featured") val featured: Boolean,
    @Json(name = "total_photos") val totalPhotos: Int,
    @Json(name = "private") val `private`: Boolean,
    @Json(name = "share_key") val shareKey: String,
    @Json(name = "links") val links: Links,
    @Json(name = "user") val user: User,
    @Json(name = "cover_photo") val coverPhoto: CoverPhoto,
    @Json(name = "preview_photos") val previewPhotos: List<PreviewPhoto>
  ) {
    data class Links(
      @Json(name = "self") val self: String,
      @Json(name = "html") val html: String,
      @Json(name = "photos") val photos: String,
      @Json(name = "related") val related: String
    )
  
    data class User(
      @Json(name = "id") val id: String,
      @Json(name = "updated_at") val updatedAt: String,
      @Json(name = "username") val username: String,
      @Json(name = "name") val name: String,
      @Json(name = "first_name") val firstName: String,
      @Json(name = "last_name") val lastName: String?,
      @Json(name = "twitter_username") val twitterUsername: String?,
      @Json(name = "portfolio_url") val portfolioUrl: String?,
      @Json(name = "bio") val bio: String?,
      @Json(name = "location") val location: String?,
      @Json(name = "links") val links: Links,
      @Json(name = "profile_image") val profileImage: ProfileImage,
      @Json(name = "instagram_username") val instagramUsername: String?,
      @Json(name = "total_collections") val totalCollections: Int,
      @Json(name = "total_likes") val totalLikes: Int,
      @Json(name = "total_photos") val totalPhotos: Int,
      @Json(name = "total_promoted_photos") val totalPromotedPhotos: Int,
      @Json(name = "total_illustrations") val totalIllustrations: Int,
      @Json(name = "total_promoted_illustrations") val totalPromotedIllustrations: Int,
      @Json(name = "accepted_tos") val acceptedTos: Boolean,
      @Json(name = "for_hire") val forHire: Boolean,
      @Json(name = "social") val social: Social
    ) {
      data class Links(
        @Json(name = "self") val self: String,
        @Json(name = "html") val html: String,
        @Json(name = "photos") val photos: String,
        @Json(name = "likes") val likes: String,
        @Json(name = "portfolio") val portfolio: String,
        @Json(name = "following") val following: String?,
        @Json(name = "followers") val followers: String?
      )
  
      data class ProfileImage(
        @Json(name = "small") val small: String,
        @Json(name = "medium") val medium: String,
        @Json(name = "large") val large: String
      )
  
      data class Social(
        @Json(name = "instagram_username") val instagramUsername: String?,
        @Json(name = "portfolio_url") val portfolioUrl: String?,
        @Json(name = "twitter_username") val twitterUsername: String?,
        @Json(name = "paypal_email") val paypalEmail: Any?
      )
    }
  
    data class CoverPhoto(
      @Json(name = "id") val id: String,
      @Json(name = "slug") val slug: String,
      @Json(name = "alternative_slugs") val alternativeSlugs: AlternativeSlugs,
      @Json(name = "created_at") val createdAt: String,
      @Json(name = "updated_at") val updatedAt: String,
      @Json(name = "promoted_at") val promotedAt: String?,
      @Json(name = "width") val width: Int,
      @Json(name = "height") val height: Int,
      @Json(name = "color") val color: String,
      @Json(name = "blur_hash") val blurHash: String?,
      @Json(name = "description") val description: String?,
      @Json(name = "alt_description") val altDescription: String,
      @Json(name = "breadcrumbs") val breadcrumbs: List<Breadcrumb>,
      @Json(name = "urls") val urls: Urls,
      @Json(name = "links") val links: Links,
      @Json(name = "likes") val likes: Int,
      @Json(name = "liked_by_user") val likedByUser: Boolean,
      @Json(name = "current_user_collections") val currentUserCollections: List<Any?>,
      @Json(name = "sponsorship") val sponsorship: Any?,
      @Json(name = "topic_submissions") val topicSubmissions: TopicSubmissions?,
      @Json(name = "asset_type") val assetType: String,
      @Json(name = "user") val user: User
    ) {
      data class AlternativeSlugs(
        @Json(name = "en") val en: String,
        @Json(name = "es") val es: String,
        @Json(name = "ja") val ja: String,
        @Json(name = "fr") val fr: String,
        @Json(name = "it") val `it`: String,
        @Json(name = "ko") val ko: String,
        @Json(name = "de") val de: String,
        @Json(name = "pt") val pt: String
      )
  
      data class Breadcrumb(
        @Json(name = "title") val title: String
      )
  
      data class Urls(
        @Json(name = "raw") val raw: String,
        @Json(name = "full") val full: String,
        @Json(name = "regular") val regular: String,
        @Json(name = "small") val small: String,
        @Json(name = "thumb") val thumb: String,
        @Json(name = "small_s3") val smallS3: String
      )
  
      data class Links(
        @Json(name = "self") val self: String,
        @Json(name = "html") val html: String,
        @Json(name = "download") val download: String,
        @Json(name = "download_location") val downloadLocation: String
      )
  
      data class TopicSubmissions(
        @Json(name = "wallpapers") val wallpapers: Wallpapers?,
        @Json(name = "textures-patterns") val texturesPatterns: TexturesPatterns?,
        @Json(name = "spirituality") val spirituality: Spirituality?,
        @Json(name = "health") val health: Health?,
        @Json(name = "travel") val travel: Travel?,
        @Json(name = "spring") val spring: Spring?,
        @Json(name = "nature") val nature: Nature?,
        @Json(name = "experimental") val experimental: Experimental?,
        @Json(name = "film") val film: Film?,
        @Json(name = "3d-renders") val dRenders: DRenders?,
        @Json(name = "sports") val sports: Sports?
      ) {
        data class Wallpapers(
          @Json(name = "status") val status: String,
          @Json(name = "approved_on") val approvedOn: String?
        )
  
        data class TexturesPatterns(
          @Json(name = "status") val status: String,
          @Json(name = "approved_on") val approvedOn: String?
        )
  
        data class Spirituality(
          @Json(name = "status") val status: String,
          @Json(name = "approved_on") val approvedOn: String?
        )
  
        data class Health(
          @Json(name = "status") val status: String
        )
  
        data class Travel(
          @Json(name = "status") val status: String
        )
  
        data class Spring(
          @Json(name = "status") val status: String,
          @Json(name = "approved_on") val approvedOn: String?
        )
  
        data class Nature(
          @Json(name = "status") val status: String,
          @Json(name = "approved_on") val approvedOn: String?
        )
  
        data class Experimental(
          @Json(name = "status") val status: String,
          @Json(name = "approved_on") val approvedOn: String?
        )
  
        data class Film(
          @Json(name = "status") val status: String
        )
  
        data class DRenders(
          @Json(name = "status") val status: String,
          @Json(name = "approved_on") val approvedOn: String?
        )
  
        data class Sports(
          @Json(name = "status") val status: String,
          @Json(name = "approved_on") val approvedOn: String?
        )
      }
  
      data class User(
        @Json(name = "id") val id: String,
        @Json(name = "updated_at") val updatedAt: String,
        @Json(name = "username") val username: String,
        @Json(name = "name") val name: String,
        @Json(name = "first_name") val firstName: String,
        @Json(name = "last_name") val lastName: String?,
        @Json(name = "twitter_username") val twitterUsername: String?,
        @Json(name = "portfolio_url") val portfolioUrl: String?,
        @Json(name = "bio") val bio: String?,
        @Json(name = "location") val location: String?,
        @Json(name = "links") val links: Links,
        @Json(name = "profile_image") val profileImage: ProfileImage,
        @Json(name = "instagram_username") val instagramUsername: String?,
        @Json(name = "total_collections") val totalCollections: Int,
        @Json(name = "total_likes") val totalLikes: Int,
        @Json(name = "total_photos") val totalPhotos: Int,
        @Json(name = "total_promoted_photos") val totalPromotedPhotos: Int,
        @Json(name = "total_illustrations") val totalIllustrations: Int,
        @Json(name = "total_promoted_illustrations") val totalPromotedIllustrations: Int,
        @Json(name = "accepted_tos") val acceptedTos: Boolean,
        @Json(name = "for_hire") val forHire: Boolean,
        @Json(name = "social") val social: Social
      ) {
        data class Links(
          @Json(name = "self") val self: String,
          @Json(name = "html") val html: String,
          @Json(name = "photos") val photos: String,
          @Json(name = "likes") val likes: String,
          @Json(name = "portfolio") val portfolio: String,
          @Json(name = "following") val following: String?,
          @Json(name = "followers") val followers: String?
        )
  
        data class ProfileImage(
          @Json(name = "small") val small: String,
          @Json(name = "medium") val medium: String,
          @Json(name = "large") val large: String
        )
  
        data class Social(
          @Json(name = "instagram_username") val instagramUsername: String?,
          @Json(name = "portfolio_url") val portfolioUrl: String?,
          @Json(name = "twitter_username") val twitterUsername: String?,
          @Json(name = "paypal_email") val paypalEmail: Any?
        )
      }
    }
  
    data class PreviewPhoto(
      @Json(name = "id") val id: String,
      @Json(name = "slug") val slug: String,
      @Json(name = "created_at") val createdAt: String,
      @Json(name = "updated_at") val updatedAt: String,
      @Json(name = "blur_hash") val blurHash: String?,
      @Json(name = "asset_type") val assetType: String,
      @Json(name = "urls") val urls: Urls
    ) {
      data class Urls(
        @Json(name = "raw") val raw: String,
        @Json(name = "full") val full: String,
        @Json(name = "regular") val regular: String,
        @Json(name = "small") val small: String,
        @Json(name = "thumb") val thumb: String,
        @Json(name = "small_s3") val smallS3: String
      )
    }
  }
