Azure Container Hands-on lab  
Mar. 2023

### 参考情報

- [名前付け規則を定義する](#ttps://docs.microsoft.com/ja-jp/azure/cloud-adoption-framework/ready/azure-best-practices/resource-naming)

- [Azure リソースの種類に推奨される省略形](#https://docs.microsoft.com/ja-jp/azure/cloud-adoption-framework/ready/azure-best-practices/resource-abbreviations)

<br />

### 共通リソースの展開

<br />

[![Deploy to Azure](https://aka.ms/deploytoazurebutton)](https://portal.azure.com/#create/Microsoft.Template/uri/https%3A%2F%2Fraw.githubusercontent.com%2Fkohei3110%2FAzureContainerApps-Hands-on-Lab-1%2Fmain%2Ftemplates%2Fdeploy-vnet-hub.json)


### パラメーター

- **virtualNetwork**: 仮想ネットワーク名（2 ～ 64 文字/英数字、アンダースコア、ピリオド、およびハイフン）

- **addressPrefix**: IPv4 アドレス空間

- **subnet1**: サブネットの名前 (1)（1 ～ 80 文字/英数字、アンダースコア、ピリオド、およびハイフン）

- **subnet1Prefix**: サブネット アドレス範囲 (1)

- **bastionPrefix**: AzureBastionSubnet サブネットのアドレス範囲

- **bastionHost**: Bastion リソースの名前（1 ～ 80 文字/英数字、アンダースコア、ピリオド、およびハイフン）

※ 事前にリソース グループの作成が必要

※ 選択したリソース グループのリージョンにすべてのリソースを展開

<br />

### リソースの展開

<br />

[![Deploy to Azure](https://aka.ms/deploytoazurebutton)](https://portal.azure.com/#create/Microsoft.Template/uri/https%3A%2F%2Fraw.githubusercontent.com%2Fkohei3110%2FAzureContainerApps-Hands-on-Lab-1%2Fmain%2Ftemplates%2Fdeploy-resources.json)

### パラメーター

- **virtualNetwork**: 仮想ネットワーク名（2 ～ 64 文字/英数字、アンダースコア、ピリオド、およびハイフン）

- **addressPrefix**: IPv4 アドレス空間

- **subnet1**: サブネットの名前 (1)（1 ～ 80 文字/英数字、アンダースコア、ピリオド、およびハイフン）

- **subnet1Prefix**: サブネット アドレス範囲 (1)

- **networkSecurityGroup**: ネットワーク セキュリティ グループ名（2 ～ 64 文字/英数字、アンダースコア、ピリオド、およびハイフン）

- **loadtestingWorkspace**: Azure Load Testing (1 ～ 64 文字/英数字、アンダースコア、およびハイフン）)

- **logAnalyticsWorkspace**: Log Analytics ワークスペース名（長さ：4 ～ 63 / 有効な文字：英数字、およびハイフン）

- **managedEnvironment**: Container App 環境名

※ 事前にリソース グループの作成が必要

※ 選択したリソース グループのリージョンにすべてのリソースを展開

※ リソース グループのリージョンは East US, East US2, West US2, West US3, Australia East, East Asia, UK South, North Europe のいずれかを選択

※ 展開後に手動で VNet Peering を構成ください。
