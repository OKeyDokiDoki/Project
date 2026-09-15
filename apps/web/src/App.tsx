import {
  Camera,
  ChevronRight,
  Heart,
  ScanLine,
  Shirt,
  ShoppingBag,
  Sparkles,
  UserRound,
} from "lucide-react";

const actions = [
  { icon: ScanLine, title: "开始体型扫描", detail: "建立专属身材档案" },
  { icon: Shirt, title: "虚拟试穿", detail: "快速切换服装与颜色" },
  { icon: Sparkles, title: "智能搭配", detail: "按场景发现合适穿搭" },
  { icon: Camera, title: "拍照对比", detail: "保存并分享试穿效果" },
];

export function App() {
  return (
    <main>
      <header className="topbar">
        <a className="brand" href="/" aria-label="镜界首页">
          <span className="brand-mark">镜</span>
          <span>镜界</span>
        </a>
        <nav aria-label="主导航">
          <button className="icon-button" title="收藏">
            <Heart size={20} />
          </button>
          <button className="icon-button" title="购物袋">
            <ShoppingBag size={20} />
          </button>
          <button className="profile-button">
            <UserRound size={19} />
            登录
          </button>
        </nav>
      </header>

      <section className="mirror-stage">
        <div className="stage-copy">
          <p className="eyebrow">AI SMART MIRROR</p>
          <h1>找到真正适合你的穿搭</h1>
          <p className="lead">
            精准识别体型与肤色，让每一次试穿更快、更自然、更有把握。
          </p>
          <button className="primary-button">
            立即试穿
            <ChevronRight size={19} />
          </button>
        </div>
        <div className="scan-visual" aria-label="人体扫描预览">
          <div className="scan-frame">
            <div className="person-silhouette" />
            <span className="scan-line" />
            <span className="measure measure-a">肩宽 · 分析中</span>
            <span className="measure measure-b">身形 · H 型</span>
            <span className="measure measure-c">尺码 · 待确认</span>
          </div>
        </div>
      </section>

      <section className="quick-actions" aria-label="核心功能">
        {actions.map(({ icon: Icon, title, detail }) => (
          <button className="action-item" key={title}>
            <span className="action-icon">
              <Icon size={22} />
            </span>
            <span>
              <strong>{title}</strong>
              <small>{detail}</small>
            </span>
            <ChevronRight className="action-arrow" size={18} />
          </button>
        ))}
      </section>
    </main>
  );
}

