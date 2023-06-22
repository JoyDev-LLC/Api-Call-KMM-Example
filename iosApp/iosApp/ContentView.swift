import SwiftUI
import shared
import Kingfisher

struct ContentView: View {

    @ObservedObject private var model: Model = Model()

    var body: some View {
        VStack(alignment: .center) {
            KFImage(URL(string: model.duckUrl))
                .resizable()
                .aspectRatio(contentMode: .fit)
                .frame(maxWidth: .infinity)
            Button("Get new duck!", action: { model.getDuckUrl() })
                .frame(maxWidth: .infinity)
        }.frame(maxWidth: .infinity, maxHeight: .infinity)
    }
}

class Model : ObservableObject {

    private let viewModel = DuckViewModel()

    @Published private(set) var duckUrl: String = ""

    init() {
        getDuckUrl()
    }

    func getDuckUrl() {
        viewModel.updateDuckIOS(completionHandler: { url, error in
           print(url ?? "")
            self.duckUrl = url ?? ""
        })
    }

}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}
