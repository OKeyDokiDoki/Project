<script setup lang="ts">
import { Heart, ShoppingBag, UserRound } from "lucide-vue-next";
</script>

<template>
  <div class="app-shell">
    <header class="topbar">
      <RouterLink class="brand" to="/" aria-label="镜界首页">
        <span class="brand-mark">镜</span>
        <span>镜界</span>
      </RouterLink>
      <nav aria-label="主导航">
        <button class="icon-button" title="收藏" aria-label="收藏">
          <Heart :size="20" />
        </button>
        <button class="icon-button" title="购物袋" aria-label="购物袋">
          <ShoppingBag :size="20" />
        </button>
        <button class="profile-button">
          <UserRound :size="19" />
          登录
        </button>
      </nav>
    </header>
    <RouterView />
  </div>
</template>
