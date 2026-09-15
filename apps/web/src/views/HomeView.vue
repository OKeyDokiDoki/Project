<script setup lang="ts">
import {
  Camera,
  ChevronRight,
  ScanLine,
  Shirt,
  Sparkles,
} from "lucide-vue-next";

const actions = [
  { icon: ScanLine, title: "开始体型扫描", detail: "建立专属身材档案" },
  { icon: Shirt, title: "虚拟试穿", detail: "快速切换服装与颜色" },
  { icon: Sparkles, title: "智能搭配", detail: "按场景发现合适穿搭" },
  { icon: Camera, title: "拍照对比", detail: "保存并分享试穿效果" },
];
</script>

<template>
  <main>
    <section class="mirror-stage">
      <div class="stage-copy">
        <p class="eyebrow">AI SMART MIRROR</p>
        <h1>找到真正适合你的穿搭</h1>
        <p class="lead">
          精准识别体型与肤色，让每一次试穿更快、更自然、更有把握。
        </p>
        <button class="primary-button">
          立即试穿
          <ChevronRight :size="19" />
        </button>
      </div>

      <div class="scan-visual" aria-label="人体扫描预览">
        <div class="scan-frame">
          <div class="person-silhouette" />
          <span class="scan-line" />
          <span class="measure measure-a">肩宽 · 分析中</span>
          <span class="measure measure-b">身形 · H 型</span>
          <span class="measure measure-c">尺码 · 待确认</span>
        </div>
      </div>
    </section>

    <section class="quick-actions" aria-label="核心功能">
      <button v-for="action in actions" :key="action.title" class="action-item">
        <span class="action-icon">
          <component :is="action.icon" :size="22" />
        </span>
        <span>
          <strong>{{ action.title }}</strong>
          <small>{{ action.detail }}</small>
        </span>
        <ChevronRight class="action-arrow" :size="18" />
      </button>
    </section>
  </main>
</template>
