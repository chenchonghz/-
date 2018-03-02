/** layui-v2.0.1 MIT License By http://www.layui.com */
 ;layui.define("jquery",function(i){"use strict";var a=layui.$,t=(layui.hint(),layui.device()),l="element",e="layui-this",n="layui-show",s=function(){this.config={}};s.prototype.set=function(i){var t=this;return a.extend(!0,t.config,i),t},s.prototype.on=function(i,a){return layui.onevent.call(this,l,i,a)},s.prototype.tabAdd=function(i,t){var l=".layui-tab-title",e=a(".layui-tab[lay-filter="+i+"]"),n=e.children(l),s=e.children(".layui-tab-content");return n.append('<li lay-id="'+(t.id||"")+'">'+(t.title||"unnaming")+"</li>"),s.append('<div class="layui-tab-item">'+(t.content||"")+"</div>"),y.hideTabMore(!0),y.tabAuto(),this},s.prototype.tabDelete=function(i,t){var l=".layui-tab-title",e=a(".layui-tab[lay-filter="+i+"]"),n=e.children(l),s=n.find('>li[lay-id="'+t+'"]');return y.tabDelete(null,s),this},s.prototype.tabChange=function(i,t){var l=".layui-tab-title",e=a(".layui-tab[lay-filter="+i+"]"),n=e.children(l),s=n.find('>li[lay-id="'+t+'"]');return y.tabClick(null,null,s),this},s.prototype.progress=function(i,t){var l="layui-progress",e=a("."+l+"[lay-filter="+i+"]"),n=e.find("."+l+"-bar"),s=n.find("."+l+"-text");return n.css("width",t),s.text(t),this};var o=".layui-nav",c="layui-nav-item",r="layui-nav-bar",u="layui-nav-tree",d="layui-nav-child",h="layui-nav-more",f="layui-anim layui-anim-upbit",y={tabClick:function(i,t,s){var o=s||a(this),t=t||o.parent().children("li").index(o),c=o.parents(".layui-tab").eq(0),r=c.children(".layui-tab-content").children(".layui-tab-item"),u=o.find("a"),d=c.attr("lay-filter");"javascript:;"!==u.attr("href")&&"_blank"===u.attr("target")||(o.addClass(e).siblings().removeClass(e),r.eq(t).addClass(n).siblings().removeClass(n)),layui.event.call(this,l,"tab("+d+")",{elem:c,index:t})},tabDelete:function(i,t){var l=t||a(this).parent(),n=l.index(),s=l.parents(".layui-tab").eq(0),o=s.children(".layui-tab-content").children(".layui-tab-item");l.hasClass(e)&&(l.next()[0]?y.tabClick.call(l.next()[0],null,n+1):l.prev()[0]&&y.tabClick.call(l.prev()[0],null,n-1)),l.remove(),o.eq(n).remove(),setTimeout(function(){y.tabAuto()},50)},tabAuto:function(){var i="layui-tab-more",l="layui-tab-bar",e="layui-tab-close",n=this;a(".layui-tab").each(function(){var s=a(this),o=s.children(".layui-tab-title"),c=(s.children(".layui-tab-content").children(".layui-tab-item"),'lay-stope="tabmore"'),r=a('<span class="layui-unselect layui-tab-bar" '+c+"><i "+c+' class="layui-icon">&#xe61a;</i></span>');if(n===window&&8!=t.ie&&y.hideTabMore(!0),s.attr("lay-allowClose")&&o.find("li").each(function(){var i=a(this);if(!i.find("."+e)[0]){var t=a('<i class="layui-icon layui-unselect '+e+'">&#x1006;</i>');t.on("click",y.tabDelete),i.append(t)}}),o.prop("scrollWidth")>o.outerWidth()+1){if(o.find("."+l)[0])return;o.append(r),s.attr("overflow",""),r.on("click",function(a){o[this.title?"removeClass":"addClass"](i),this.title=this.title?"":"收缩"})}else o.find("."+l).remove(),s.removeAttr("overflow")})},hideTabMore:function(i){var t=a(".layui-tab-title");i!==!0&&"tabmore"===a(i.target).attr("lay-stope")||(t.removeClass("layui-tab-more"),t.find(".layui-tab-bar").attr("title",""))},clickThis:function(){var i=a(this),t=i.parents(o),n=t.attr("lay-filter"),s=i.find("a");i.find("."+d)[0]||("javascript:;"!==s.attr("href")&&"_blank"===s.attr("target")||(t.find("."+e).removeClass(e),i.addClass(e)),layui.event.call(this,l,"nav("+n+")",i))},clickChild:function(){var i=a(this),t=i.parents(o),n=t.attr("lay-filter");t.find("."+e).removeClass(e),i.addClass(e),layui.event.call(this,l,"nav("+n+")",i)},showChild:function(){var i=a(this),t=i.parents(o),l=i.parent(),e=i.siblings("."+d);t.hasClass(u)&&(e.removeClass(f),l["none"===e.css("display")?"addClass":"removeClass"](c+"ed"))},collapse:function(){var i=a(this),t=i.find(".layui-colla-icon"),e=i.siblings(".layui-colla-content"),s=i.parents(".layui-collapse").eq(0),o=s.attr("lay-filter"),c="none"===e.css("display");if("string"==typeof s.attr("lay-accordion")){var r=s.children(".layui-colla-item").children("."+n);r.siblings(".layui-colla-title").children(".layui-colla-icon").html("&#xe602;"),r.removeClass(n)}e[c?"addClass":"removeClass"](n),t.html(c?"&#xe61a;":"&#xe602;"),layui.event.call(this,l,"collapse("+o+")",{title:i,content:e,show:c})}};s.prototype.init=function(i){var l={tab:function(){y.tabAuto.call({})},nav:function(){var i=200,l={},e={},s={},p=function(o,c,r){var y=a(this),p=y.find("."+d);c.hasClass(u)?o.css({top:y.position().top,height:y.children("a").height(),opacity:1}):(p.addClass(f),o.css({left:y.position().left+parseFloat(y.css("marginLeft")),top:y.position().top+y.height()-5}),l[r]=setTimeout(function(){o.css({width:y.width(),opacity:1})},t.ie&&t.ie<10?0:i),clearTimeout(s[r]),"block"===p.css("display")&&clearTimeout(e[r]),e[r]=setTimeout(function(){p.addClass(n),y.find("."+h).addClass(h+"d")},300))};a(o).each(function(t){var o=a(this),f=a('<span class="'+r+'"></span>'),v=o.find("."+c);o.find("."+r)[0]||(o.append(f),v.on("mouseenter",function(){p.call(this,f,o,t)}).on("mouseleave",function(){o.hasClass(u)||(clearTimeout(e[t]),e[t]=setTimeout(function(){o.find("."+d).removeClass(n),o.find("."+h).removeClass(h+"d")},300))}),o.on("mouseleave",function(){clearTimeout(l[t]),s[t]=setTimeout(function(){o.hasClass(u)?f.css({height:0,top:f.position().top+f.height()/2,opacity:0}):f.css({width:0,left:f.position().left+f.width()/2,opacity:0})},i)})),v.each(function(){var i=a(this),t=i.find("."+d);if(t[0]&&!i.find("."+h)[0]){var l=i.children("a");l.append('<span class="'+h+'"></span>')}i.off("click",y.clickThis).on("click",y.clickThis),i.children("a").off("click",y.showChild).on("click",y.showChild),t.children("dd").off("click",y.clickChild).on("click",y.clickChild)})})},breadcrumb:function(){var i=".layui-breadcrumb";a(i).each(function(){var i=a(this),t=i.attr("lay-separator")||">",l=i.find("a");l.find(".layui-box")[0]||(l.each(function(i){i!==l.length-1&&a(this).append('<span class="layui-box">'+t+"</span>")}),i.css("visibility","visible"))})},progress:function(){var i="layui-progress";a("."+i).each(function(){var t=a(this),l=t.find(".layui-progress-bar"),e=l.attr("lay-percent");l.css("width",e),t.attr("lay-showPercent")&&setTimeout(function(){var a=Math.round(l.width()/t.width()*100);a>100&&(a=100),l.html('<span class="'+i+'-text">'+a+"%</span>")},350)})},collapse:function(){var i="layui-collapse";a("."+i).each(function(){var i=a(this).find(".layui-colla-item");i.each(function(){var i=a(this),t=i.find(".layui-colla-title"),l=i.find(".layui-colla-content"),e="none"===l.css("display");t.find(".layui-colla-icon").remove(),t.append('<i class="layui-icon layui-colla-icon">'+(e?"&#xe602;":"&#xe61a;")+"</i>"),t.off("click",y.collapse).on("click",y.collapse)})})}};return layui.each(l,function(i,a){a()})};var p=new s,v=a(document);p.init();var b=".layui-tab-title li";v.on("click",b,y.tabClick),v.on("click",y.hideTabMore),a(window).on("resize",y.tabAuto),i(l,p)});